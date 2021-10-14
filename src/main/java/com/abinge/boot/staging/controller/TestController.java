package com.abinge.boot.staging.controller;

import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Student;
import com.abinge.boot.staging.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2020/9/29 14:24
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/request", method = {RequestMethod.GET, RequestMethod.POST})
    public Result testRequest(@RequestBody Student student) {
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        return Result.success(RequestUtils.getBody(request));
    }
}
