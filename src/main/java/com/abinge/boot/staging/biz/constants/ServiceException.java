package com.abinge.boot.staging.biz.constants;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -8667394300356618037L;
    protected String code;

    public ServiceException() {
    }

    public ServiceException(Throwable cause) {
        super(cause);
        if (cause instanceof ServiceException) {
            this.code = ((ServiceException) cause).getCode();
        } else {
            this.code = "1";
        }

    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(ServiceCode respCode) {
        super(respCode.message());
        this.code = respCode.code();
    }

    public ServiceException(ServiceCode respCode, Throwable cause) {
        super(respCode.message(), cause);
        this.code = respCode.code();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetailMessage() {
        return this.toString();
    }

    public String toString() {
        return "ServiceException{code='" + this.code + '\'' + "message='" + this.getMessage() + '\'' + '}';
    }
}