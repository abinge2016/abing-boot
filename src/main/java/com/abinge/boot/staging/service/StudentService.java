package com.abinge.boot.staging.service;

import com.abinge.boot.staging.model.Student;
import com.abinge.boot.staging.repository.StudentRepository;
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
 * @date 2019/1/5 16:09
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Mono<Student> add(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Student> update(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Student> delete(Student student) {
        studentRepository.delete(student);
        return Mono.just(student);
    }

    public Mono<Student> queryById(Long id) {
        return studentRepository.findById(id);
    }

    public Flux<Student> queryAll() {
        return studentRepository.findAll();
    }

    public Flux<Student> queryAll(Student student) {
        return studentRepository.findAll();
    }

}
