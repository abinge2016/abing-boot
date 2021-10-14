package com.abinge.boot.staging.service;

import com.abinge.boot.staging.model.Teacher;
import com.abinge.boot.staging.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.service
 * @description TODO
 * @date 2019/1/5 16:22
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Mono<Teacher> add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Mono<Teacher> update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Mono<Teacher> delete(Teacher teacher) {
        teacherRepository.delete(teacher);
        return Mono.just(teacher);
    }

    public Mono<Teacher> queryById(Long id) {
        return teacherRepository.findById(id);
    }

    public Flux<Teacher> queryAll() {
        return teacherRepository.findAll();
    }

    public Flux<Teacher> queryAll(Teacher teacher) {
        return teacherRepository.findAll();
    }
}
