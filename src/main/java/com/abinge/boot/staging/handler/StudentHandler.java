package com.abinge.boot.staging.handler;

import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Student;
import com.abinge.boot.staging.repository.StudentRepository;
import com.abinge.boot.staging.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class StudentHandler {
    @Autowired
    private StudentRepository studentRepository;

    public Mono<ServerResponse> add(ServerRequest serverRequest) {
        Mono<Student> student = serverRequest.bodyToMono(Student.class);
        return student.flatMap(stu -> {
            // 参数校验
            ParamCheckUtil.checkSaveStudent(stu);

            Mono<Result> resultMono = studentRepository.saveAll(student).collectList().flatMap(stuList -> Mono.just(Result.success(stuList.get(0))));
            return ok().contentType(MediaType.APPLICATION_JSON)
                    .body(resultMono, Result.class);
        });

    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String idStr = serverRequest.pathVariable("id");
        Long id = Long.valueOf(idStr);
        return studentRepository.findById(id)
                .flatMap(inDb ->
                    studentRepository.deleteById(id).then(ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(Result.success(inDb)),Result.class)))
                .switchIfEmpty(ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(Result.fail("未找到对应的数据")), Result.class));
    }

    public Mono<ServerResponse> queryById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentRepository.findById(Mono.just(Long.valueOf(id))).flatMap(stu -> Mono.just(Result.success(stu))), Result.class);
    }

    public Mono<ServerResponse> queryAll(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentRepository.findAll().collectList().flatMap(stuList -> Mono.just(Result.success(stuList))), Result.class);
    }

}
