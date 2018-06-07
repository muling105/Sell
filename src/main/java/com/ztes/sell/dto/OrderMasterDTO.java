package com.ztes.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ztes.sell.enums.OrderStatusEnum;
import com.ztes.sell.enums.PayStatusEnum;
import com.ztes.sell.pojo.OrderDetail;
import com.ztes.sell.utils.EnumUtil;
import com.ztes.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 传输层
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) //值为Null时，不返回
public class OrderMasterDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    //订单状态 默认0代表新订单
    private Integer orderStatus;
    //支付状态 默认0代表未支付
    private Integer payStatus;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderEnum(Integer orderStatus){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayEnum(Integer payStatus){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
