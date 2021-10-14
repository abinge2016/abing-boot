package com.abinge.boot.staging.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.model
 * @description TODO
 * @date 2019/1/5 17:37
 */
public class Result<T> implements Serializable {

    private final static String SUCCESS_CODE = "0";
    private final static String SUCCESS_MSG = "成功";
    private final static String FAIL_CODE = "1";
    private final static String FAIL_MSG = "系统异常";

    public String code;
    public String msg;
    public T data;

    public Result() { }

    public static <T> Result success(){
        return success(SUCCESS_MSG,StringUtils.EMPTY);
    }
    public static <T>  Result success(T data){
        return success(SUCCESS_MSG,data);
    }

    public static <T> Result success(String msg,T data){
        return new Result(SUCCESS_CODE,msg,data);
    }

    public static <T> Result fail(){
        return fail(FAIL_MSG,StringUtils.EMPTY);
    }

    public static <T> Result fail(String msg){
        return fail(msg,StringUtils.EMPTY);
    }

    public static <T> Result fail(String msg,T data){
        return new Result(FAIL_CODE,msg,data);
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
