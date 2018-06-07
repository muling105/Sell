package com.ztes.sell.service.impl;

import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.enums.OrderStatusEnum;
import com.ztes.sell.enums.PayStatusEnum;
import com.ztes.sell.pojo.OrderDetail;
import com.ztes.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception{
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName("张三");
        orderMasterDTO.setBuyerOpenid("12213");
        orderMasterDTO.setBuyerPhone("13890901919");
        orderMasterDTO.setBuyerAddress("齐鲁软件园");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail od01 = new OrderDetail();
        od01.setProductId("1001");
        od01.setProductQuant(1);
        orderDetailList.add(od01);
        OrderDetail od02 = new OrderDetail();
        od02.setProductId("1002");
        od02.setProductQuant(1);
        orderDetailList.add(od02);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        OrderMasterDTO result = orderService.create(orderMasterDTO);
        log.info("[创建订单] result = {}", result);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void findOne() throws Exception{
        OrderMasterDTO orderMasterDTO = orderService.findOne("15277521007765586579");
        log.info("[订单]  result = {}", orderMasterDTO);
        Assert.assertNotNull(orderMasterDTO);
    }

    @Test
    @Transactional
    public void findList() throws Exception{
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderMasterDTO> orderMasterDTOPage = orderService.findList("12213", pageRequest);
        log.info("[订单] result = {}", orderMasterDTOPage.getContent());
        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception{
        OrderMasterDTO orderMasterDTO = orderService.findOne("15277521007765586579");
        OrderMasterDTO od01 = orderService.cancel(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), od01.getOrderStatus());
    }

    @Test
    public void finish() throws Exception{
        OrderMasterDTO orderMasterDTO = orderService.findOne("15277521007765586579");
        OrderMasterDTO od01 = orderService.finish(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), od01.getOrderStatus());
    }

    @Test
    public void paid() throws Exception{
        OrderMasterDTO orderMasterDTO = orderService.findOne("15277521007765586579");
        OrderMasterDTO od01 = orderService.paid(orderMasterDTO);
        Assert.assertEquals(PayStatusEnum.UNPAID.getCode(), od01.getPayStatus());
    }

    @Test
    public void findPageList() throws Exception{
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderMasterDTO> orderMasterDTOPage = orderService.findList(pageRequest);
        log.info("[订单] result = {}", orderMasterDTOPage.getContent());
        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
    }
}