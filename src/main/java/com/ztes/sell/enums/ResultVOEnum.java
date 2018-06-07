package com.ztes.sell.enums;

import lombok.Getter;

@Getter
public enum ResultVOEnum {
    SUCCESS(1, "成功"),
    ERROR(0, "失败")
    ;

    //错误码
    private int code;
    //错误信息
    private String msg;

    ResultVOEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
