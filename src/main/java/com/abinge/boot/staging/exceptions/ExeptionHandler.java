package com.abinge.boot.staging.exceptions;

import com.abinge.boot.staging.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.exceptions
 * @description TODO
 * @date 2019/1/5 18:04
 */
@RestControllerAdvice
@Slf4j
public class ExeptionHandler {

    @ExceptionHandler(BizException.class)
    public Result handBizException(BizException e) {
        log.error("process system error", e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handException(Exception e) {
        log.error("process system error", e);
        return Result.fail();
    }

}
