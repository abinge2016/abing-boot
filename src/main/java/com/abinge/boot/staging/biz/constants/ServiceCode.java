package com.abinge.boot.staging.biz.constants;

public interface ServiceCode extends ResultCode {
    default ServiceException failure() {
        return new ServiceException(this);
    }

    default ServiceException failure(Throwable cause) {
        return new ServiceException(this, cause);
    }
}