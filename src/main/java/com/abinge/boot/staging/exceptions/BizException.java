package com.abinge.boot.staging.exceptions;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.exceptions
 * @description TODO
 * @date 2019/1/5 17:59
 */
public class BizException extends RuntimeException{
    public BizException(){super();}

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message,cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public static BizException build(String message){
        return new BizException(message);
    }

    public static BizException build(String message, Throwable cause){
        return new BizException(message,cause);
    }
    public static BizException build(Throwable cause){
        return new BizException(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
