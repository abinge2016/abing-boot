package com.abinge.boot.staging.controller;//package com.abinge.boot.staging.controller;
//
//import com.abinge.boot.staging.model.ApiParameter;
//import com.abinge.boot.staging.model.Result;
//import com.abinge.boot.staging.model.Student;
//import com.abinge.boot.staging.utils.RequestUtils;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Map;
//
//import static org.springframework.web.bind.annotation.RequestMethod.*;
//
//@Controller
//@RequestMapping("/router")
//@Slf4j
//public class RouterTestController {
//
//    @Autowired
//    HttpServletRequest request;
//
//    @Autowired
//    HttpServletResponse response;
//
//    @RequestMapping(value = "/list",method = RequestMethod.POST)
//    @ResponseBody
//    public Object list(@RequestBody Student student){
//        return null;
//    }
//
//    @PostMapping(value = "/relation")
//    @ResponseBody
//    public Result<Student> orgStatus(){
//        return null;
//    }
//
//    @GetMapping(value = "/rest")
//    public Object testParam(){
////        Object body = RequestUtils.getBody(request);
//        Map<String, String[]> body = RequestUtils.getParameterMapIncludeJSON(request);
//        ApiParameter apiParameter = new ApiParameter();
//        apiParameter.setParameters(body);
//        log.info("body：{}", JSON.toJSONString(apiParameter));
//        return Result.success(apiParameter.getParameters());
//    }
//
//    @PostMapping("testJson")
//    public Object testJson(@RequestBody Student student){
//        String jsonBody = getJSONBody(request);
//        log.info("getJSONBody：{}", jsonBody);
//        String result = JSON.toJSONString(student);
//        log.info("result：{}",result);
//        return result;
//    }
//
//
//    /**
//     * 1、dubbo接口：Object、List、基础数据类型
//     * 2、http接口：Object、List、基础数据类型
//     *
//     * 基础数据类型：Form表单、URL拼接、JSON数据
//     * Object：Form表单、URL拼接、JSON数据
//     * List：Form表单、ULR拼接、JSONArray数据
//     *
//     *
//     *
//     *
//     *
//     * @param request
//     * @return
//     */
//    private String getBody(HttpServletRequest request){
//        StringBuilder sb = new StringBuilder();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        sb.append("parameterMap：").append(JSON.toJSONString(parameterMap));
//        String[] requestBodies = parameterMap.get("requestBody");
//        if (null != requestBodies && requestBodies.length > 0){
//            String json = requestBodies[0];
//            if (StringUtils.isNotBlank(json)){
//                JSONObject jsonObject = JSON.parseObject(json);
//                log.info("jsonObject：{}", jsonObject.toJSONString());
//            }
//        }
//        String queryString = request.getQueryString();
//        sb.append(", queryString：").append(queryString);
//        return sb.toString();
//    }
//
//    private String getJSONBody(HttpServletRequest request){
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//        try {
//            InputStream inputStream = request.getInputStream();
//            if (inputStream != null) {
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                char[] charBuffer = new char[128];
//                int bytesRead = -1;
//                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                    stringBuilder.append(charBuffer, 0, bytesRead);
//                }
//            } else {
//                stringBuilder.append("");
//            }
//        } catch (Exception ex) {
//            log.error("getJSONBody error",ex);
//            return StringUtils.EMPTY;
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    log.error("getJSONBody error",ex);
//                    return StringUtils.EMPTY;
//                }
//            }
//        }
//        return stringBuilder.toString();
//    }
//}
