package com.ztes.sell.dao;

import com.ztes.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String>{

    List<OrderDetail> findByOrderId(String orderId) throws Exception;

}
