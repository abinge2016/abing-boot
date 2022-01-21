package com.abinge.boot.staging.biz.controller;

import com.abinge.boot.staging.biz.model.Result;
import com.abinge.boot.staging.biz.model.Student;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * 测试验证
 *
 * @author abinge
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/post_json")
    public Mono<Result> postJson(@RequestBody Student student) {
        log.info("post_json, req:{}", JSON.toJSONString(student));
        return Mono.just(Result.success(student));
    }

    @PostMapping("/post_form")
    public Mono<Result> postForm(@RequestParam String name,
                                          @RequestParam Integer age) {
        log.info("post_form, name:{}, age:{}", name, age);
        return Mono.just(Result.success(Student.builder().name(name).age(age).build()));
    }

    @PostMapping("/post_list")
    public Mono<Result> postList(@RequestBody List<String> names) {
        log.info("post_list, names:{}", names);
        return Mono.just(Result.success(names));
    }

    @GetMapping("/get_list")
    public Mono<Result> getList(@RequestParam List<String> names) {
        log.info("get_list, names:{}", names);
        return Mono.just(Result.success(names));
    }

    @PostMapping("/upload_file")
    public Mono<Result> uploadFile(@RequestPart("file") Mono<FilePart> file) {
        return file.flatMap(filePart -> {
            String filename = filePart.filename();
            log.info("upload_file, file:{}", filename);
            return Mono.just(Result.success(filename));
        });
    }
}
