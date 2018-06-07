package com.ztes.sell.dao;

import com.ztes.sell.pojo.OrderDetail;
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
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail bean = new OrderDetail();
        bean.setDetailId("1302");
        bean.setOrderId("1201");
        bean.setProductId("1003");
        bean.setProductName("珍珠奶茶");
        bean.setProductPrice(new BigDecimal(10));
        bean.setProductQuant(1);
        bean.setProductIcon("http://*******.jgp");
        OrderDetail od = orderDetailDao.save(bean);
        Assert.assertNotNull(od);
    }

    @Test
    @Transactional
    public void findByOrderId() throws Exception{
        List<OrderDetail> list = orderDetailDao.findByOrderId("1201");
        Assert.assertNotEquals(0, list.size());
    }
}