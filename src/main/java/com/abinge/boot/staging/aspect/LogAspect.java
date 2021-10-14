package com.abinge.boot.staging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LogAspect {
    private static final Instant now = Instant.now();

    @Around("@annotation(Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = now.toEpochMilli();
        Object result = joinPoint.proceed();
        log.info("Enter: {}.{}() with argument[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                joinPoint.getArgs());
        log.info("Exit: {}.{}() had arguments = {}, with result = {}, Execution time = {} ms",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                joinPoint.getArgs(),
                Flux.just(result), (now.toEpochMilli() - start));
        return result;
    }
}
