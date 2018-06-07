package com.ztes.sell.utils;

import com.ztes.sell.enums.CodeEnum;

//枚举根据Code值获取枚举
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> classEnum){
        for(T each : classEnum.getEnumConstants()){
            if(each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }

}
