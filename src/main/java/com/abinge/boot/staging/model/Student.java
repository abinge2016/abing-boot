package com.abinge.boot.staging.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.model
 * @description TODO
 * @date 2019/1/5 15:56
 */
@Entity
@Data
public class Student extends BaseModel {
    private String name;
    private String age;
    private int grade;
    private short sex;
    private String className;

    private List<String> friends;
//    private List<Student> studentList;

}
