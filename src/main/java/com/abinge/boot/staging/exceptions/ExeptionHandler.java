package com.abinge.boot.staging.exceptions;

import com.abinge.boot.staging.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

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
    public Mono<Result> handBizException(BizException e) {
        log.error("process system biz error", e);
        return Mono.just(Result.fail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<Result> handException(Exception e) {
        log.error("process system error", e);
        return Mono.just(Result.fail());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<Result> handBindException(WebExchangeBindException e) {
        log.error("process web exchange bind error", e);
        String errMsg = e.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + "：" + fieldError.getDefaultMessage())
                .reduce(((s1, s2) -> s1 + " \n" + s2))
                .orElse(StringUtils.EMPTY);
        return Mono.just(Result.fail(errMsg));
    }

}
