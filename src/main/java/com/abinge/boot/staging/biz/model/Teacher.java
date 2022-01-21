package com.abinge.boot.staging.biz.model;

import lombok.Data;

@Data
public class Teacher extends BaseModel {
    private String name;
    private int age;
    private String course;
}