package com.sea.handler;

import com.alibaba.fastjson.JSONObject;
import com.sea.constants.ErrorCodeEnum;
import com.sea.controller.BackendLoginController;
import com.sea.exception.BackendLoginException;
import com.sea.model.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lvhaizhen on 2018/8/9.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public String allExceptionHandler(HttpServletRequest request,
                                          Exception exception) throws Exception {

        //如果是缺少参数。
        if(exception instanceof MissingServletRequestParameterException){
            return JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.NEED_PARAMS.code,
                    ErrorCodeEnum.NEED_PARAMS.message + exception.getLocalizedMessage()));
        }else if(exception instanceof BackendLoginException){
            return JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.NEED_LOGIN.code,
                    ErrorCodeEnum.NEED_LOGIN.message + exception.getLocalizedMessage()));
        }else{
            LOG.error("未知错误",exception);
            return JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.EXCEPION.code,
                    ErrorCodeEnum.EXCEPION.message + exception.getLocalizedMessage()));
        }
    }
}
