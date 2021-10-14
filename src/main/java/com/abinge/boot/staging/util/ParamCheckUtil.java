package com.abinge.boot.staging.util;

import com.abinge.boot.staging.exceptions.BizException;
import com.abinge.boot.staging.model.Student;
import com.abinge.boot.staging.model.Teacher;
import org.apache.commons.lang3.StringUtils;

public class ParamCheckUtil {
    public static void checkSaveStudent(Student student){
        if (StringUtils.isBlank(student.getName())){
            throw new BizException("name can not be blank");
        }
        if (StringUtils.isBlank(student.getAge())){
            throw new BizException("age can not be blank");
        }
    }

    public static void checkSaveTeacher(Teacher teacher) {
        if (StringUtils.isBlank(teacher.getName())){
            throw new BizException("name can not be blank");
        }

    }
}
