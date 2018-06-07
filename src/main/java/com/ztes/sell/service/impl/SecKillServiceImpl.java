package com.ztes.sell.service.impl;

import com.ztes.sell.exception.SellException;
import com.ztes.sell.service.RedisLock;
import com.ztes.sell.service.SecKillService;
import com.ztes.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀商品，测试Redis分布式锁
 */
@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {

    //超时时间 10秒
    private static final Integer TIMEOUT = 10 * 1000;

    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static{
        /**
         * 模拟多个表，商品信息，库存表，秒杀成功订单
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    @Autowired
    private RedisLock redisLock;

    private String queryMap(String productId){
        return "活动特价，限量："
                + products.get(productId)
                + "   剩余：" + stock.get(productId)
                + "   成功下单用户数："
                + orders.size() + "人。";
    }

    @Override
    public String querySkillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductDiffUser(String productId) {

        Long time = System.currentTimeMillis() + TIMEOUT;
        //加锁
        if(!redisLock.lock(productId, String.valueOf(time))){
            throw new SellException(202, "没有抢到，请再接再厉！");
        }

        //查询商品库存， 0表示活动结束
        int stockNum = stock.get(productId);
        if(stockNum == 0){
            log.info("【活动结束】");
            throw new SellException(201, "活动结束");
        }else {
            //下单
            orders.put(KeyUtil.generate().toString(), productId);
            //减库存
            stockNum --;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        //解锁
        redisLock.unLock(productId, String.valueOf(time));

    }
}
