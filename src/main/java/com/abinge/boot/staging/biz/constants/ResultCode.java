package com.abinge.boot.staging.biz.constants;

public interface ResultCode {
    String SYS = "SYS";
    String COMMON = "C";
    String AUTH = "AUTH";

    String code();

    String message();
}