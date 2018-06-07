package com.ztes.sell.service.impl;

import com.ztes.sell.dao.ProductCategoryDao;
import com.ztes.sell.pojo.ProductCategory;
import com.ztes.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory getOne(Integer categoryId) throws Exception{
        return productCategoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() throws Exception{
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) throws Exception{
        return productCategoryDao.findByCategoryTypeIn(list);
    }

    @Override
    public void save(ProductCategory bean) throws Exception{
        productCategoryDao.save(bean);
    }
}
