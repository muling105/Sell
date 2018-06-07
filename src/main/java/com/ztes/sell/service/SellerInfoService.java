package com.ztes.sell.service;

import com.ztes.sell.pojo.SellerInfo;

public interface SellerInfoService {

    SellerInfo save(SellerInfo bean) throws Exception;

    SellerInfo findByOpenid(String openid) throws Exception;

}
