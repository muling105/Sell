package com.ztes.sell.VO;

import lombok.Data;

/**
 *  http 返回数据格式
 */
@Data
public class ResultVO <T>{

    //错误码
    private int code;
    //错误信息
    private String msg;
    //数据（泛型）
    private T data;

}
