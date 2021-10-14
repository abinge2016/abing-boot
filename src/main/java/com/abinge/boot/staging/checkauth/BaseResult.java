/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.checkauth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2021/4/30 10:35
 */
@Data
@NoArgsConstructor
public class BaseResult<T> {
    private final static String SUCCESS_CODE = "0";
    private final static String SUCCESS_MSG = "成功";
    private final static String FAIL_CODE = "1";
    private final static String FAIL_MSG = "系统异常";
    private String code;
    private String message;
    public T data;

    public Boolean isSuccess(){
        return SUCCESS_CODE.equals(getCode());
    }
    public static <T> BaseResult success(){
        return success(SUCCESS_MSG, StringUtils.EMPTY);
    }
    public static <T>  BaseResult success(T data){
        return success(SUCCESS_MSG,data);
    }

    public static <T> BaseResult success(String message,T data){
        return new BaseResult(SUCCESS_CODE,message,data);
    }

    public static <T> BaseResult fail(){
        return fail(FAIL_MSG,StringUtils.EMPTY);
    }

    public static <T> BaseResult fail(String message){
        return fail(message,StringUtils.EMPTY);
    }

    public static <T> BaseResult fail(String message,T data){
        return new BaseResult(FAIL_CODE,message,data);
    }

    private BaseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
