package com.abinge.boot.staging.controller;

import com.abinge.boot.staging.aspect.Loggable;
import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Student;
import com.abinge.boot.staging.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.controller
 * @description TODO
 * @date 2019/1/5 16:23
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    @Loggable
    public Mono<Result> add(@Valid @RequestBody Student student) {
        return studentService.add(student).flatMap(stu -> Mono.just(Result.success(stu)));
    }

    @PostMapping("/update")
    @Loggable
    public Mono<Result> update(@RequestBody @Valid Student student) {
        return studentService.update(student).flatMap(stu -> Mono.just(Result.success(stu)));
    }

    @PostMapping("/delete")
    @Loggable
    public Mono<Result> delete(@RequestBody Student student) {
        return studentService.delete(student).flatMap(stu -> Mono.just(Result.success(stu)));
    }

    @PostMapping("/queryById")
    @Loggable
    public Mono<Result> queryById(Long id) {
        return studentService.queryById(id).flatMap(stu -> Mono.just(Result.success(stu)));
    }

    @GetMapping(value = "/queryAll")
    @Loggable
    public Mono<Result> queryAll() {
        long start = System.currentTimeMillis();
        Flux<Student> result = studentService.queryAll();
        System.out.printf("queryAll : " + (System.currentTimeMillis() - start));
        return result.collectList().flatMap(students -> Mono.just(Result.success(students)));
    }

    @PostMapping(value = "/queryAll")
    @Loggable
    public Mono<Result> queryAll(@RequestBody Student student) {
        long start = System.currentTimeMillis();
        Flux<Student> result = studentService.queryAll(student);
        System.out.printf("queryAll : " + (System.currentTimeMillis() - start));
        return result.collectList().flatMap(students -> Mono.just(Result.success(students)));
    }
}
