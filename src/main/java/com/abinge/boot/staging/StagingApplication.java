package com.abinge.boot.staging;

import com.abinge.boot.staging.model.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package com.abinge.boot.staging
 * @description 脚手架工程
 * @author abinge
 * @date 2019/1/5 15:45
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019/1/5</p>
 */
//@EnableJpaAuditing
@SpringBootApplication
public class StagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StagingApplication.class, args);
    }

    @RestController
    public static class EchoTest {

        @GetMapping("/")
        public Result<String> echo() {
            return Result.success("OpenApiAdapterApplication");
        }
    }

}

