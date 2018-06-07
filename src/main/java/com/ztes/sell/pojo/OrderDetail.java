package com.ztes.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 */
@Entity
@Data
public class OrderDetail implements Serializable{

    private static final long serialVersionUID = 4879147569419130379L;

    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuant;
    private String productIcon;
    private Date createTime;

}
