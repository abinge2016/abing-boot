//package com.abinge.boot.staging.utils;
//
//import com.abinge.boot.staging.checkauth.AppDO;
//import com.abinge.boot.staging.checkauth.PageResult;
//import com.abinge.boot.staging.checkauth.ServicesDO;
//import com.abinge.boot.staging.wedoctor.RoleApiSimpleVO;
//import com.abinge.boot.staging.wedoctor.RoleSimpleVO;
//import com.abinge.boot.staging.wedoctor.UserAccountSubVO;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.TypeReference;
//import com.guahao.convention.data.domain.DefaultPage;
//import com.guahao.convention.data.domain.Page;
//import com.guahao.convention.data.domain.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.client.CookieStore;
//
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//public class WedoctorCloudUtil {
//
//    public static List<AppDO> queryApps(String url, Map<String, String> parameters, CookieStore httpCookieStore) {
//        String resultJson = HttpClientUtil.doPostFormWithCookie(url, parameters, httpCookieStore);
//        log.info("frog应用查询响应：{}", resultJson);
//        if (StringUtils.isBlank(resultJson)){
//            log.info("frog应用查询失败");
//        }
//        PageResult<AppDO> appPageResult = JSON.parseObject(resultJson, new TypeReference<PageResult<AppDO>>() {});
//        List<AppDO> apps = appPageResult.getRows();
//        log.info("frog应用查询结果，数量：{}", apps.size());
//        return apps;
//    }
//
//    public static Page<UserAccountSubVO> queryUserAccountSub(String url, String parameterJson, CookieStore httpCookieStore) {
//        String resultJson = HttpClientUtil.doPostJsonWithCookie(url, parameterJson, httpCookieStore);
//        log.info("应用查询响应：{}", resultJson);
//        if (StringUtils.isBlank(resultJson)){
//            log.info("应用查询失败");
//        }
//        JSONObject jsonObject = JSON.parseObject(resultJson);
//        String data = jsonObject.getString("data");
//        DefaultPage<UserAccountSubVO> apps = JSON.parseObject(data, new TypeReference<DefaultPage<UserAccountSubVO>>() {});
//        log.info("应用查询结果，数量：{}", apps.getTotalCount());
//        return apps;
//    }
//
//    public static List<RoleApiSimpleVO> queryUserAccountSubDetail(String url, String contentType, CookieStore httpCookieStore) {
//        String resultJson = HttpClientUtil.doGetWithCookie(url, contentType, httpCookieStore);
//        log.info("子应用详情查询响应：{}", resultJson);
//        if (StringUtils.isBlank(resultJson)){
//            log.info("子应用详情查询失败");
//        }
//        Result<List<RoleApiSimpleVO>> pageResult = JSON.parseObject(resultJson, new TypeReference<Result<List<RoleApiSimpleVO>>>() {});
//        List<RoleApiSimpleVO> apps = pageResult.getData();
//        log.info("子应用详情查询结果，数量：{}", apps.size());
//        return apps;
//    }
//
//    public static Page<RoleSimpleVO> queryUserAccountSubRole(String url, String parameterJson, CookieStore httpCookieStore) {
//        String resultJson = HttpClientUtil.doPostJsonWithCookie(url, parameterJson, httpCookieStore);
//        log.info("应用角色查询响应：{}", resultJson);
//        if (StringUtils.isBlank(resultJson)){
//            log.info("应用角色查询失败");
//        }
//        JSONObject jsonObject = JSON.parseObject(resultJson);
//        String data = jsonObject.getString("data");
//        Page<RoleSimpleVO> apps = JSON.parseObject(data, new TypeReference<DefaultPage<RoleSimpleVO>>() {});
//        log.info("应用角色查询结果，数量：{}", apps.getTotalCount());
//        return apps;
//    }
//
//
//
//    public static List<RoleApiSimpleVO> queryRoleServices(String url, String contentType, CookieStore httpCookieStore) {
//        String resultJson = HttpClientUtil.doGetWithCookie(url, contentType, httpCookieStore);
//        log.info("服务列表查询响应：{}", resultJson);
//        if (StringUtils.isBlank(resultJson)){
//            log.info("服务列表查询失败");
//        }
//        JSONObject jsonObject = JSON.parseObject(resultJson);
//        String data = jsonObject.getString("data");
//        List<RoleApiSimpleVO> apps = JSON.parseObject(data, new TypeReference<List<RoleApiSimpleVO>>() {});
//        log.info("服务列表查询结果，数量：{}", apps.size());
//        return apps;
//    }
//
//    public static List<ServicesDO> queryAppServices(String url, Map<String, String> parameters, CookieStore httpCookieStore) {
//        String resultJson = HttpClientUtil.doPostFormWithCookie(url, parameters, httpCookieStore);
//        log.info("frog应用分配服务权限查询响应：{}", resultJson);
//        if (StringUtils.isBlank(resultJson)){
//            log.info("frog应用分配服务权限查询失败");
//        }
//        PageResult<ServicesDO> appServicePageResult = JSON.parseObject(resultJson, new TypeReference<PageResult<ServicesDO>>() {});
//        List<ServicesDO> appServices = appServicePageResult.getRows();
//        return appServices;
//    }
//
//    public static String stsCheckAuth(String stsCheckAuthUrl, Map<String, String> headers, String parameterJson) {
//        String resultJson = HttpClientUtil.doPostJson(stsCheckAuthUrl, headers, parameterJson);
//        return resultJson;
//    }
//
//
//}
