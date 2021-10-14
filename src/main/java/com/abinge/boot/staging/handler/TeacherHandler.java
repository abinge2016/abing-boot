package com.abinge.boot.staging.handler;

import com.abinge.boot.staging.model.Result;
import com.abinge.boot.staging.model.Teacher;
import com.abinge.boot.staging.repository.TeacherRepository;
import com.abinge.boot.staging.util.ParamCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TeacherHandler {
    @Autowired
    private TeacherRepository TeacherRepository;

    public Mono<ServerResponse> add(ServerRequest serverRequest) {
        Mono<Teacher> Teacher = serverRequest.bodyToMono(Teacher.class);
        return Teacher.flatMap(tea -> {
            // 参数校验
            ParamCheckUtil.checkSaveTeacher(tea);

            Mono<Result> resultMono = TeacherRepository.saveAll(Teacher).collectList().flatMap(teaList -> Mono.just(Result.success(teaList.get(0))));
            return ok().contentType(MediaType.APPLICATION_JSON)
                    .body(resultMono, Result.class);
        });

    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String idStr = serverRequest.pathVariable("id");
        Long id = Long.valueOf(idStr);
        return TeacherRepository.findById(id)
                .flatMap(inDb ->
                        TeacherRepository.deleteById(id).then(ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(Result.success(inDb)),Result.class)))
                .switchIfEmpty(ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(Result.fail("未找到对应的数据")), Result.class));
    }

    public Mono<ServerResponse> queryById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(TeacherRepository.findById(Mono.just(Long.valueOf(id))).flatMap(tea -> Mono.just(Result.success(tea))), Result.class);
    }

    public Mono<ServerResponse> queryAll(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(TeacherRepository.findAll().collectList().flatMap(teaList -> Mono.just(Result.success(teaList))), Result.class);
    }

}
