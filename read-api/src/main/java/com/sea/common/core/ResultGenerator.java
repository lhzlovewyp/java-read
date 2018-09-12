package com.sea.common.core;

import com.sea.common.constants.ErrorCodeEnum;
import com.sea.model.dto.ResultDTO;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static ResultDTO genSuccessResult() {
        return new ResultDTO(ErrorCodeEnum.LOGIN_SUCCESS.code,ErrorCodeEnum.LOGIN_SUCCESS.message);
    }

    public static <T> ResultDTO<T> genSuccessResult(T data) {
        return new ResultDTO(ErrorCodeEnum.LOGIN_SUCCESS.code,ErrorCodeEnum.LOGIN_SUCCESS.message,data);

    }

    public static ResultDTO genFailResult(String message) {
        return new ResultDTO(ErrorCodeEnum.NEED_PARAMS.code,message);
    }
}
