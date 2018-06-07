package com.ztes.sell.service;

import com.ztes.sell.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    //创建订单
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO) throws Exception;

    //查询订单
    OrderMasterDTO findOne(String orderId) throws Exception;

    //查询订单列表
    Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) throws Exception;

    //取消订单
    OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) throws Exception;

    //完成订单
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) throws Exception;

    //支付
    OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) throws Exception;

    //查询订单列表
    Page<OrderMasterDTO> findList(Pageable pageable) throws Exception;

}
