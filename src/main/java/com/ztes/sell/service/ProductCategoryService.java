package com.ztes.sell.service;

import com.ztes.sell.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory getOne(Integer categoryId) throws Exception;

    List<ProductCategory> findAll() throws Exception;

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list) throws Exception;

    void save(ProductCategory bean) throws Exception;

}
