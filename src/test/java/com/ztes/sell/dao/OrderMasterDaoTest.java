package com.ztes.sell.dao;

import com.ztes.sell.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void saveTest(){
        OrderMaster bean = new OrderMaster();
        bean.setOrderId("1202");
        bean.setBuyerName("李四");
        bean.setBuyerAddress("银荷大厦");
        bean.setBuyerOpenid("12211");
        bean.setBuyerPhone("13890901919");
        OrderMaster om = orderMasterDao.save(bean);
        Assert.assertNotNull(om);
    }

    @Test
    @Transactional
    public void findByBuyerOpenid() throws Exception{
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMaster> page = orderMasterDao.findByBuyerOpenid("12211", pageRequest);
        Assert.assertNotEquals(0, page.getContent().size());
    }
}