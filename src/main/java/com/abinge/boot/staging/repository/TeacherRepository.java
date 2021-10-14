package com.abinge.boot.staging.repository;

import com.abinge.boot.staging.model.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.dao
 * @description TODO
 * @date 2019/1/5 16:06
 */
public interface TeacherRepository extends ReactiveCrudRepository<Teacher, Long> {
}
