package com.ztes.sell.aspect;

import com.ztes.sell.constant.CookieConstant;
import com.ztes.sell.constant.RedisConstant;
import com.ztes.sell.exception.SellerAuthorizeException;
import com.ztes.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.ztes.sell.controller.Seller*.*(..)) "
    + "&& !execution(public * com.ztes.sell.controller.SellerUserController.*(..))")
    public void verify(){}

    //拦截是否存在cookie -- 是否登录
    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("没有查找到Cookie中token");
            throw new SellerAuthorizeException();
        }
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PROFIX, cookie.getValue()));
        if(tokenValue == null){
            log.warn("没有查找到Redis中token");
            throw new SellerAuthorizeException();
        }

    }

}
