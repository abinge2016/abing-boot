package com.abinge.boot.staging.biz.model.config;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final String SUCCESS_CODE = "0";
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.code);
    }
}
