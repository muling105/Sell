package com.ztes.sell.pojo;

import com.ztes.sell.enums.OrderStatusEnum;
import com.ztes.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.function.BiConsumer;

/**
 * 订单表
 */
@Entity
@Data
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = -8243893088189791583L;

    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    //订单总价
    private BigDecimal orderAmount;
    //订单状态 默认0代表新订单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态 默认0代表未支付
    private Integer payStatus = PayStatusEnum.PREPAID.getCode();
    private Date createTime;

}
