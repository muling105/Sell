package com.ztes.sell.dao;

import com.ztes.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo bean = new ProductInfo();
        bean.setProductId("1001");
        bean.setProductName("荷叶粥");
        bean.setProductPrice(new BigDecimal(2));
        bean.setProductDescription("健康");
        bean.setProductIcon("http://*********.jpg");
        bean.setProductStock(20);
        bean.setProductStatus(0);
        bean.setCategoryType(2);
        productInfoDao.save(bean);
    }

    @Test
    @Transactional
    public void findByProductStatus() {
        List<ProductInfo> list = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0, list.size());
    }
}