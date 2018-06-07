package com.ztes.sell.service;

import com.ztes.sell.dto.OrderMasterDTO;

public interface BuyerService {

    OrderMasterDTO findOrderOne(String orderId, String openid) throws Exception;

    OrderMasterDTO cancel(String orderId, String openid) throws Exception;
}
