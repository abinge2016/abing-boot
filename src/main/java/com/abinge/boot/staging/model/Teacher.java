package com.abinge.boot.staging.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.model
 * @description TODO
 * @date 2019/1/5 15:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher extends BaseModel {
    @NotBlank
    private String name;
    private int age;
    private String course;
}
