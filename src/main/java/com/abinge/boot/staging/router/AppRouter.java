package com.abinge.boot.staging.router;

import com.abinge.boot.staging.handler.StudentHandler;
import com.abinge.boot.staging.handler.TeacherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter {
    @Bean
    RouterFunction<ServerResponse> studentRouter(StudentHandler studentHandler){
        // 此处就相当于是Controller上面的@RequestMapping("/student")
        return nest(path("/student"),
                    // 下面就相当于是Controller类中的各个方法上的@RequestMapping("/add")
                    route(POST("/add").and(accept(MediaType.APPLICATION_JSON)), studentHandler::add)
                    .andRoute(POST("/update").and(accept(MediaType.APPLICATION_JSON)), studentHandler::add)
                    .andRoute(POST("/delete/{id}"), studentHandler::delete)
                    .andRoute(GET("/queryById/{id}"), studentHandler::queryById)
                    .andRoute(GET("/queryAll"), studentHandler::queryAll)
                );
    }

    @Bean
    RouterFunction<ServerResponse> teacherRouter(TeacherHandler teacherHandler){
        return nest(path("/teacher"),
                    route(POST("/add").and(accept(MediaType.APPLICATION_JSON)), teacherHandler::add)
                    .andRoute(POST("/update").and(accept(MediaType.APPLICATION_JSON)), teacherHandler::add)
                    .andRoute(POST("/delete").and(accept(MediaType.APPLICATION_JSON)), teacherHandler::delete)
                    .andRoute(POST("/queryById").and(accept(MediaType.APPLICATION_JSON)), teacherHandler::queryById)
                    .andRoute(GET("/queryAll"), teacherHandler::queryAll)
                );
    }
}
