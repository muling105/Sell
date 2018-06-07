package com.ztes.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Redis分布式锁
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 过期时间
     * @return
     */
    public Boolean lock(String key, String value){
        // 如果当前锁不存在，返回加锁成功
        if(redisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁的时间过期了
        if(!StringUtils.isEmpty(currentValue) &&
                Long.valueOf(currentValue) < System.currentTimeMillis()){
            //锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                //考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权利加锁
                return true;
            }
        }

        //其他情况，一律返回加锁失败
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unLock(String key, String value){
        try{
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【Redis解锁异常】 {}", e);
        }
    }


}
