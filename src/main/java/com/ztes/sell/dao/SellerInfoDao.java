package com.ztes.sell.dao;

import com.ztes.sell.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoDao extends JpaRepository<SellerInfo, String>{

    SellerInfo findByOpenid(String openid);

}
