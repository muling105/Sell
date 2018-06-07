package com.ztes.sell.controller;

import com.ztes.sell.VO.ResultVO;
import com.ztes.sell.service.BuyerService;
import com.ztes.sell.utils.ResultVOUtil;
import com.ztes.sell.converter.OrderFormToOrderMasterDTO;
import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.enums.ResultEnum;
import com.ztes.sell.exception.SellException;
import com.ztes.sell.form.OrderForm;
import com.ztes.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @RequestMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            log.error("[订单参数错误] orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMasterDTO orderMasterDTO = OrderFormToOrderMasterDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("[购物车不能为空] result = {}", orderForm);
            throw new SellException(ResultEnum.CART_NOT_EMPTY);
        }

        OrderMasterDTO omd = orderService.create(orderMasterDTO);
        Map map = new HashMap();
        map.put("orderId", omd.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @RequestMapping("/list")
    public ResultVO<List<OrderMasterDTO>> list(@RequestParam(value="openid")String openid,
            @RequestParam(value="pageNum", defaultValue = "0")Integer pageNum,
            @RequestParam(value="pageSize", defaultValue = "10")Integer pageSize) throws Exception{
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        if(StringUtils.isEmpty(openid)){
            log.error("[openid不能为空]");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderMasterDTO> orderMasterDTOList = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderMasterDTOList.getContent());
    }

    //订单详情
    @RequestMapping("/detail")
    public ResultVO<OrderMasterDTO> detail(@RequestParam(value="openid")String openid,
                                           @RequestParam(value="orderId")String orderId) throws Exception{
        OrderMasterDTO orderMasterDTO = buyerService.findOrderOne(orderId, openid);
        return ResultVOUtil.success(orderMasterDTO);
    }

    //取消订单
    @RequestMapping("/cancel")
    public ResultVO cancel(@RequestParam(value="openid")String openid,
                           @RequestParam(value="orderId")String orderId) throws Exception{
        buyerService.cancel(orderId, openid);
        return ResultVOUtil.success();
    }

}
