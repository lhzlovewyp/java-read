package com.sea.common.handler;

import com.sea.common.cache.redis.RedisUtil;
import com.sea.common.constants.LoginConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.sea.common.exception.BackendLoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录拦截器,拦截所有/backend/xxx的请求.
 *
 * Created by lvhaizhen on 2018/7/25.
 */
@Controller
@Component
public class BackendLoginHandler implements HandlerInterceptor {


    @Autowired
    private RedisUtil redisUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();


        if(isNeedLogin(requestUrl)){
            String token = request.getHeader("token");

            //当前未登录.
            Object obj = redisUtil.get("token");
            if(obj == null){
                throw new BackendLoginException("当前请求需要登录.");
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


    private boolean isNeedLogin(String requestUrl){
        if(requestUrl.indexOf(LoginConstant.BACKEND_PREFIX) < 0){
            return false;
        }

        for(String path : LoginConstant.NOT_LOGIN_URL_LIST){
            if(requestUrl.indexOf(path) >= 0){
                return false;
            }
        }
        return true;
    }
}
