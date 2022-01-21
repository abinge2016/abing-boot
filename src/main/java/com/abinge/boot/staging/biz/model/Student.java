package com.abinge.boot.staging.biz.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student extends BaseModel {
    private String name;
    private Integer age;
    private Integer grade;
    private Integer sex;
    private String className;

}