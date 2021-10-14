package com.abinge.boot.staging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019</p>
 * @projectName staging
 * @package com.abinge.boot.staging.model
 * @description 基础类
 * @date 2019/1/5 15:51
 *
 * @MappedSuperclass 增加该主键表示允许各子类在使用@Entity注解时，生成对应的表，且在表中包含本基类的各个字段
 * @EntityListeners(AuditingEntityListener.class) 添加此注解，下面的@CreatedDate和@LastModifiedDate才会生效
 *
 */
public class BaseModel implements Serializable {
    /**
     * 主键id，且设置为自增
     */
    @Id
    protected Long id;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    protected short isDeleted;
}
