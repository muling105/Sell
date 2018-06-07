package com.ztes.sell.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单"),
    FINISH(1, "完结订单"),
    CANCEL(2, "取消订单")
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
