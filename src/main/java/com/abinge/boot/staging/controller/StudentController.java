package com.abinge.boot.staging.controller;

import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Student;
import com.abinge.boot.staging.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result add(@RequestBody Student student) {
        return Result.success(studentService.add(student));
    }

    @PostMapping("/update")
    public Result update(Student student) {
        return Result.success(studentService.update(student));
    }

    @PostMapping("/delete")
    public Result delete(Student student) {
        return Result.success(studentService.delete(student));
    }

    @PostMapping("/queryById")
    public Result queryById(Long id) {
        return Result.success(studentService.queryById(id));
    }

    @GetMapping("/queryAll")
    public Result queryAll() {
        return Result.success(studentService.queryAll());
    }

    @PostMapping("/queryAll")
    public Result queryAll(Student student) {
        return Result.success(studentService.queryAll(student));
    }
}
