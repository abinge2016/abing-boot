package com.abinge.boot.staging.service;

import com.abinge.boot.staging.dao.StudentRepository;
import com.abinge.boot.staging.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.saveAndFlush(student);
    }
    public Student update(Student student, String studentName) {
        return studentRepository.saveAndFlush(student);
    }

    public Student delete(Student student) {
        studentRepository.delete(student);
        return student;
    }

    public Student queryById(Long id) {
        return studentRepository.getOne(id);
    }

    public List<Student> queryAll() {
        return studentRepository.findAll();
    }

    public List<Student> queryAll(Student student) {
        Example<Student> example = Example.of(student);
        return studentRepository.findAll(example);
    }

}
