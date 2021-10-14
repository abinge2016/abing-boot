package com.abinge.boot.staging.controller;

import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Teacher;
import com.abinge.boot.staging.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public Mono<Result> add(@RequestBody @Valid Teacher teacher) {
        return teacherService.add(teacher).flatMap(tea -> Mono.just(Result.success(tea)));
    }

    @PostMapping("/update")
    public Mono<Result> update(@RequestBody @Valid Teacher teacher) {
        return teacherService.update(teacher).flatMap(tea -> Mono.just(Result.success(tea)));
    }

    @PostMapping("/delete")
    public Mono<Result> delete(@RequestBody Teacher teacher) {
        return teacherService.delete(teacher).flatMap(tea -> Mono.just(Result.success(tea)));
    }

    @PostMapping("/queryById")
    public Mono<Result> queryById(Long id) {
        return teacherService.queryById(id).flatMap(tea -> Mono.just(Result.success(tea)));
    }

    @GetMapping("/queryAll")
    public Mono<Result> queryAll() {
        return teacherService.queryAll().collectList().flatMap(teaList -> Mono.just(Result.success(teaList)));
    }

    @PostMapping("/queryAll")
    public Mono<Result> queryAll(@RequestBody Teacher teacher) {
        return teacherService.queryAll(teacher).collectList().flatMap(teaList -> Mono.just(Result.success(teaList)));
    }
}
