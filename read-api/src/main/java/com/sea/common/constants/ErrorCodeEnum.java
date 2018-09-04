package com.sea.common.constants;

/**
 * Created by lvhaizhen on 2018/7/25.
 */
public enum ErrorCodeEnum {


    LOGIN_SUCCESS("0","登录成功."),
    NEED_LOGIN("-1","需要登录."),
    NEED_PARAMS("-11","参数缺失:"),
    EXCEPION("-12","未知错误:"),
    ERROR_KAPTCHACODE("-13","登录失败,验证码校验失败."),
    ERROR_USERNAME("-14","登录失败,用户名密码错误."),
    ERROR_KAPTCHACODE_CHECK_MAX("-15","验证码超过上限,请重新获取验证码."),

    ERROR_400("-2","400错误.");

    public String code;
    public String message;

    private ErrorCodeEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
