package com.abinge.boot.staging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @package com.abinge.boot.staging
 * @description 脚手架工程
 * @author abinge
 * @date 2019/1/5 15:45
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019/1/5</p>
 */
@EnableJpaAuditing
@SpringBootApplication
public class StagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StagingApplication.class, args);
    }

}

