package com.sea.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sea.cache.redis.RedisUtil;
import com.sea.constants.ErrorCodeEnum;
import com.sea.constants.LoginConstant;
import com.sea.exception.BackendLoginException;
import com.sea.model.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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
