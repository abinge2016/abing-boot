package com.abinge.boot.staging.service;

import com.abinge.boot.staging.dao.TeacherRepository;
import com.abinge.boot.staging.model.Teacher;
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
 * @date 2019/1/5 16:22
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher update(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    public Teacher delete(Teacher teacher) {
        teacherRepository.delete(teacher);
        return teacher;
    }

    public Teacher queryById(Long id) {
        return teacherRepository.getOne(id);
    }

    public List<Teacher> queryAll() {
        return teacherRepository.findAll();
    }

    public List<Teacher> queryAll(Teacher teacher) {
        Example<Teacher> example = Example.of(teacher);
        return teacherRepository.findAll(example);
    }
}
