package com.ztes.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(99, "参数错误"),
    PRODUCT_NOT_EXIST(101, "商品不存在"),
    PRODUCT_IS_FEW(102, "库存不足"),
    ORDER_NOT_EXIST(103, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(104, "订单详情不存在"),
    ORDER_STATUS_ERROR(105, "订单状态不正确"),
    ORDER_UPDATE_FAIL(106, "订单更新失败"),
    ORDER_PAY_STATUS_ERROR(107, "订单支付状态不正确"),
    CART_NOT_EMPTY(108, "购物车不能为空"),
    OPENID_NOT_EQUAL(109, "微信openid不同"),
    PRODUCT_STATUS_ERROR(110, "商品状态不正确"),
    UNKOWN_ERROR(90, "未知错误")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
