package com.abinge.boot.staging.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @package com.abinge.boot.staging.interceptor
 * @description 日志记录拦截器
 * @author abinge
 * @date 2019/1/5 18:46
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019/1/5</p>
 */
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        //统一打印日志
        String requestURL = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        //原始请求地址
        String ip = httpServletRequest.getLocalAddr();
        //请求参数
        String params = httpServletRequest.getQueryString();
        log.info("IP：{}，method：{}，url：{}?{}", ip, method, requestURL, params);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
