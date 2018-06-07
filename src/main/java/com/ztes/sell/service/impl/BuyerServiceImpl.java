package com.ztes.sell.service.impl;

import com.ztes.sell.dao.OrderMasterDao;
import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.enums.OrderStatusEnum;
import com.ztes.sell.enums.ResultEnum;
import com.ztes.sell.exception.SellException;
import com.ztes.sell.service.BuyerService;
import com.ztes.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderMasterDTO findOrderOne(String orderId, String openid) throws Exception {
        if(StringUtils.isEmpty(openid)){
            log.error("[openid不能为空]");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if(StringUtils.isEmpty(orderId)){
            log.error("[orderId不能为空]");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderMasterDTO orderMasterDTO = orderService.findOne(orderId);
        if(orderMasterDTO == null){
            log.error("[订单不存在] orderId = {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(!orderMasterDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[openid不同] openid = {}, orderMasterDTO={}", openid, orderMasterDTO);
            throw new SellException(ResultEnum.OPENID_NOT_EQUAL);
        }
        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO cancel(String orderId, String openid) throws Exception {
        OrderMasterDTO orderMasterDTO = findOrderOne(orderId, openid);
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态不正确] orderStatus = {}", orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderService.cancel(orderMasterDTO);
        return orderMasterDTO;
    }
}
