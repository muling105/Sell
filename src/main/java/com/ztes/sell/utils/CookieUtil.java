package com.ztes.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    /**
     * 设置Cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response, String name,
                           String value, Integer maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    //获取Cookie
    public static Cookie get(HttpServletRequest request, String name){
        Map<String, Cookie> map = getCookies(request);
        if(map.containsKey(name)){
            return map.get(name);
        }else {
            return null;
        }
    }

    //获取前端传来的cookie数组
    private static Map<String, Cookie> getCookies(HttpServletRequest request){
        Map<String, Cookie> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                map.put(cookie.getName(), cookie);
            }
        }

        return map;
    }

}
