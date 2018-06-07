package com.ztes.sell.utils;

import java.util.Random;

/**
 * 生成主键
 */
public class KeyUtil {

    public static synchronized String generate(){
        Random random = new Random();
        Integer number = random.nextInt(9000000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
