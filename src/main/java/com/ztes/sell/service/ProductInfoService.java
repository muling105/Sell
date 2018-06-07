package com.ztes.sell.service;

import com.ztes.sell.dto.CarDTO;
import com.ztes.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 */
public interface ProductInfoService {

    ProductInfo getOne(String productId) throws Exception;

    //查询上架商品
    List<ProductInfo> getUpAll() throws Exception;

    Page<ProductInfo> getAll(Pageable pageable) throws Exception;

    ProductInfo save(ProductInfo bean) throws Exception;

    //加库存
    void increaseStock(List<CarDTO> carDTOList) throws Exception;

    //减库存
    void decreaseStock(List<CarDTO> carDTOList) throws Exception;

    //商品上架
    ProductInfo onSale(String productId) throws Exception;

    ProductInfo offSale(String productId) throws Exception;

}
