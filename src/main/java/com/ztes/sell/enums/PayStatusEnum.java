package com.ztes.sell.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    PREPAID(0, "未支付"),
    UNPAID(1, "已支付")
    ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
