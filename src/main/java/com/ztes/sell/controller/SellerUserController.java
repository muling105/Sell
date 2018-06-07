package com.ztes.sell.controller;

import com.ztes.sell.constant.CookieConstant;
import com.ztes.sell.constant.RedisConstant;
import com.ztes.sell.pojo.SellerInfo;
import com.ztes.sell.service.SellerInfoService;
import com.ztes.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/seller/user")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletResponse response, @RequestParam("openid")String openid) throws Exception{
        SellerInfo sellerInfo = sellerInfoService.findByOpenid(openid);
        if(sellerInfo == null){
            return new ModelAndView("common/error");
        }
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        //设置Redis数据 并设置数据过期时间
        template.opsForValue().set(String.format(RedisConstant.TOKEN_PROFIX, token), openid, expire, TimeUnit.SECONDS);

        //设置token
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("seller/success");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        //获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null){
            //清除Redis 相关数据
            template.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PROFIX, cookie.getValue()));

            //清除Cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        return new ModelAndView("seller/logout");
    }


}
