package com.ztes.sell.service.impl;

import com.ztes.sell.dao.SellerInfoDao;
import com.ztes.sell.pojo.SellerInfo;
import com.ztes.sell.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Transactional
    public SellerInfo save(SellerInfo bean) throws Exception{
        return sellerInfoDao.save(bean);
    }

    @Transactional
    public SellerInfo findByOpenid(String openid) throws Exception{
        return sellerInfoDao.findByOpenid(openid);
    }

}
