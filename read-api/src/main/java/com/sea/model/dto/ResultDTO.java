package com.sea.model.dto;

import java.io.Serializable;

/**
 *
 * api接口返回结果集合.
 *
 * Created by lvhaizhen on 2018/7/25.
 */
public class ResultDTO<T> implements Serializable{

    /**
     * 接口返回的状态编号
     */
    private String rtnCode;

    /**
     * 接口返回状态描述
     */
    private String rtnMsg;

    /**
     * 接口返回的具体异常信息
     */
    private String rtnExt;

    /**
     * 接口返回的业务对象
     *
     */
    private T rtnData;

    public ResultDTO(String rtnCode,String rtnMsg){
        this.rtnCode = rtnCode;
        this.rtnMsg = rtnMsg;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public String getRtnExt() {
        return rtnExt;
    }

    public void setRtnExt(String rtnExt) {
        this.rtnExt = rtnExt;
    }

    public T getRtnData() {
        return rtnData;
    }

    public void setRtnData(T rtnData) {
        this.rtnData = rtnData;
    }
}
