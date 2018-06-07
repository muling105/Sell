package com.ztes.sell.dao;

import com.ztes.sell.pojo.SellerInfo;
import com.ztes.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.generate());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("abc123");
        SellerInfo result = sellerInfoDao.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void findByOpenidTest() {
        SellerInfo result = sellerInfoDao.findByOpenid("abc123");
        Assert.assertNotNull(result);
    }
}