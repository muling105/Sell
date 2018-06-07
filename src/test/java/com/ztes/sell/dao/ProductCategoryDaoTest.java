package com.ztes.sell.dao;

import com.ztes.sell.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Transactional
    public void findOne(){
        ProductCategory productCategory = productCategoryDao.getOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void save(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("饮料");
        productCategory.setCategoryType(4);
        productCategoryDao.save(productCategory);
    }

    @Test
    @Transactional
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2, 3);
        List<ProductCategory> pc = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, pc);
    }

}