package com.abinge.boot.staging.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.model
 * @description TODO
 * @date 2019/1/5 15:56
 */

@Data
public class Student extends BaseModel {
    @NotBlank
    private String name;
    @NotBlank
    private String age;
    private int grade;
    private short sex;
    private String className;

}
