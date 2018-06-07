package com.ztes.sell.dao;

import com.ztes.sell.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo, String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
