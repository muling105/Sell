package com.ztes.sell.service.impl;

import com.ztes.sell.converter.OrderMasterToOrderDTOConverter;
import com.ztes.sell.dao.OrderDetailDao;
import com.ztes.sell.dao.OrderMasterDao;
import com.ztes.sell.dto.CarDTO;
import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.enums.OrderStatusEnum;
import com.ztes.sell.enums.PayStatusEnum;
import com.ztes.sell.enums.ResultEnum;
import com.ztes.sell.exception.SellException;
import com.ztes.sell.pojo.OrderDetail;
import com.ztes.sell.pojo.OrderMaster;
import com.ztes.sell.pojo.ProductInfo;
import com.ztes.sell.service.OrderService;
import com.ztes.sell.service.ProductInfoService;
import com.ztes.sell.service.WebSocket;
import com.ztes.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) throws Exception{
        String orderId = KeyUtil.generate();

        //订单详情
        BigDecimal orderAmount = new BigDecimal(0);
        for(OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.getOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuant())).add(orderAmount);

            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.generate());
            orderDetail.setOrderId(orderId);
            //存储订单详情
            orderDetailDao.save(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.PREPAID.getCode());
        //存储订单
        orderMasterDao.save(orderMaster);

        //产品减库存
        List<CarDTO> carDTOList = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CarDTO(e.getProductId(), e.getProductQuant()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(carDTOList);

        //发送WebSocket消息到客户端
        webSocket.sendMessage("您有新的订单，请查看！");

        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO findOne(String orderId)  throws Exception{
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderMasterDTO orderMasterDTO = OrderMasterToOrderDTOConverter.covert(orderMaster);
        orderMasterDTO.setOrderDetailList(orderDetailList);

        return orderMasterDTO;
    }

    @Override
    @Transactional
    public Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) throws Exception{
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDTO> orderMasterDTOList = OrderMasterToOrderDTOConverter.covertList(orderMasterPage.getContent());
        Page<OrderMasterDTO> orderMasterDTOPage = new PageImpl<>(orderMasterDTOList,
                pageable, orderMasterPage.getTotalElements());

        return orderMasterDTOPage;
    }

    //取消订单
    @Override
    @Transactional
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态不正确] orderStatus = {}", orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMasterDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        //更新订单
        OrderMaster updateOrderMaster = orderMasterDao.save(orderMaster);
        if(updateOrderMaster == null){
            log.error("[订单更新失败] orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        if(CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("[没有订单详情] orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        //返回库存
        List<CarDTO> carDTOList = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CarDTO(e.getProductId(), e.getProductQuant()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(carDTOList);

        //若已支付，则退款
        if(orderMasterDTO.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())){
            //TODO
        }

        return orderMasterDTO;
    }

    //完结订单
    @Override
    @Transactional
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态不正确] orderStatus = {}", orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMasterDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        //更新订单
        OrderMaster updateOrderMaster = orderMasterDao.save(orderMaster);
        if(updateOrderMaster == null){
            log.error("[订单更新失败] orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderMasterDTO;
    }

    //订单支付
    @Override
    @Transactional
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态不正确] orderStatus = {}", orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        if(!orderMasterDTO.getPayStatus().equals(PayStatusEnum.PREPAID.getCode())){
            log.error("[订单支付状态不正确] orderStatus = {}", orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderMasterDTO.setPayStatus(PayStatusEnum.UNPAID.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        //更新订单
        OrderMaster updateOrderMaster = orderMasterDao.save(orderMaster);
        if(updateOrderMaster == null){
            log.error("[订单更新失败] orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderMasterDTO;
    }

    @Override
    public Page<OrderMasterDTO> findList(Pageable pageable) throws Exception{
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);
        List<OrderMasterDTO> orderMasterDTOList = OrderMasterToOrderDTOConverter.covertList(orderMasterPage.getContent());
        Page<OrderMasterDTO> orderMasterDTOPage = new PageImpl<>(orderMasterDTOList,
                pageable, orderMasterPage.getTotalElements());

        return orderMasterDTOPage;
    }
}
