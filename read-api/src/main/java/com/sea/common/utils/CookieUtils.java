package com.sea.common.utils;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lvhaizhen on 2018/9/7.
 */
public class CookieUtils {

    private static final String DOMAIN = "rsfight.com";

    public static void addCookie(HttpServletResponse response,String name, String value, Integer expireDays,boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(DOMAIN);
        cookie.setPath("/");
        cookie.setHttpOnly(httpOnly);

        if (expireDays!=null && expireDays >= 0) {
            cookie.setMaxAge(expireDays*24*60*60);
        }
        if (value==null || value.length()==0) {
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
    }


    public static String getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equalsIgnoreCase(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
