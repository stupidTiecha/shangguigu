package com.tiehca.apitest.heshang.bean.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResp {

    private Integer status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    private BaseResp() {

    }

    private BaseResp(Object data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    private BaseResp(Integer i) {
        this.status = i;
    }

    private BaseResp(Object data) {
        this.data = data;
        this.status = 0;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static BaseResp success() {
        return new BaseResp(0);
    }

    public static BaseResp success(Object data) {
        return new BaseResp(data);
    }

    public static BaseResp success(Object data, String msg) {
        return  new BaseResp(data, msg);
    }

    public static BaseResp failed () {
        return  new BaseResp(1);
    }

    public static BaseResp failed (String msg){
        BaseResp failed = BaseResp.failed();
        failed.setMsg(msg);
        return failed;
    }
}
