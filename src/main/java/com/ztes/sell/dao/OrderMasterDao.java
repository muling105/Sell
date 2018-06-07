package com.ztes.sell.dao;

import com.ztes.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable) throws Exception;

}
