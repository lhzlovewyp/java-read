package com.sea.constants;

/**
 * Created by lvhaizhen on 2018/7/25.
 */
public enum ErrorCodeEnum {


    NEED_LOGIN("-1","需要登录."),
    NEED_PARAMS("-11","参数缺失:"),
    EXCEPION("-12","未知错误:"),
    ERROR_400("-2","400错误.");

    public String code;
    public String message;

    private ErrorCodeEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
