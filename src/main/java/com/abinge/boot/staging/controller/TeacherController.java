package com.abinge.boot.staging.controller;

import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Teacher;
import com.abinge.boot.staging.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result add(Teacher teacher) {
        return Result.success(teacherService.add(teacher));
    }

    @PostMapping("/update")
    public Result update(Teacher teacher) {
        return Result.success(teacherService.update(teacher));
    }

    @PostMapping("/delete")
    public Result delete(Teacher teacher) {
        return Result.success(teacherService.delete(teacher));
    }

    @PostMapping("/queryById")
    public Result queryById(Long id) {
        return Result.success(teacherService.queryById(id));
    }

    @GetMapping("/queryAll")
    public Result queryAll() {
        return Result.success(teacherService.queryAll());
    }

    @PostMapping("/queryAll")
    public Result queryAll(Teacher teacher) {
        return Result.success(teacherService.queryAll(teacher));
    }
}
