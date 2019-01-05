package com.abinge.boot.staging.dao;

import com.abinge.boot.staging.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.dao
 * @description 学生类
 * @date 2019/1/5 16:00
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
