///*
// * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
// * This software is the confidential and proprietary information of GuaHao Company.
// * ("Confidential Information").
// * You shall not disclose such Confidential Information and shall use it only
// * in accordance with the terms of the license agreement you entered into with GuaHao.com.
// */
//package com.abinge.boot.staging.checkauth;
//
//import cn.hutool.core.io.FileUtil;
//import com.abinge.boot.staging.utils.MockLoginUtil;
//import com.abinge.boot.staging.utils.WedoctorCloudUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.client.CookieStore;
//import org.apache.http.impl.client.BasicCookieStore;
//
//import java.io.File;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @author chenbj
// * @version 1.0.0
// * @time 2021/4/30 10:34
// */
//@Slf4j
//public class FrogAppStsCheckAuth {
//
//    private static final String ONLINE_LOGIN_URL = "http://user.gops.guahao.cn/dologin";
//    private static final String ONLINE_QUERY_APP_URL = "http://frog.gops.guahao.cn/apps/list.json";
//    private static final String ONLINE_QUERY_APP_SERVICES_URL = "http://frog.gops.guahao.cn/apps/searchServiceAuth.json";
////    private static final String ONLINE_STS_CHECK_AUTH_URL = "http://10.20.84.125:9636/api/authenticate/check_auth";
//    private static final String ONLINE_STS_CHECK_AUTH_URL = "http://ram-gw-cloud.guahao.cn//api/authenticate/check_auth";
//    private static final String ONLINE_PRODUCT_CODE = "104nH39f8";
//
//
////    private static final String TEST_LOGIN_URL = "http://user.guahao-test.com/dologin";
////    private static final String TEST_QUERY_APP_URL = "http://frog.guahao-test.com//apps/list.json";
////    private static final String TEST_QUERY_APP_SERVICES_URL = "http://frog.guahao-test.com/apps/searchServiceAuth.json";
////    private static final String TEST_STS_CHECK_AUTH_URL = "http://wedoctor-cloud-sts-service-stable.guahao-test.com/api/authenticate/check_auth";
////    private static final String TEST_PRODUCT_CODE = "1j94V22q1";
//
//
//    private static final String LOGIN_ID = "chenbj";
//    private static final String LOGIN_PWD = "HXcbj0524@";
//
//
//
//    public static void main(String[] args) {
////        checkFromFrogGops();
//        List<AppServiceInfo> appServiceInfos = getAppServiceInfosFromFile("20210513_frog应用数据.json");
//        stsCheckAuth(appServiceInfos);
//        write2File(appServiceInfos, "20210513_frog应用sts预发鉴权结果-2.txt");
//    }
//
//
//    private static void checkFromFrogGops(){
//        List<AppServiceInfo> appServiceInfos = queryFrogAppServiceInfos(ONLINE_LOGIN_URL, ONLINE_QUERY_APP_URL, ONLINE_QUERY_APP_SERVICES_URL);
//        FileUtil.appendUtf8String(JSON.toJSONString(appServiceInfos), new File("20210513_frog应用数据.json"));
//    }
//
//    private static void checkFromFile(String filePath){
//        List<AppServiceInfo> appServiceInfos = getAppServiceInfosFromFile(filePath);
//        String failedServiceIdJson = FileUtil.readUtf8String(new File("鉴权失败接口.json"));
//        List<String> failedServiceIds = JSON.parseArray(failedServiceIdJson, String.class);
//        List<AppServiceInfo> newAppServiceInfos = appServiceInfos.stream().filter(appServiceInfo -> !failedServiceIds.contains(appServiceInfo.getServiceId())).collect(Collectors.toList());
//
//
//        stsCheckAuth(newAppServiceInfos);
//        write2File(newAppServiceInfos, "20210430_frog应用sts预发鉴权结果-3.txt");
//    }
//
//    private static void write2File(List<AppServiceInfo> appServiceInfos,String fileName){
//        File file = new File(fileName);
//        for (AppServiceInfo appServiceInfo : appServiceInfos) {
//            FileUtil.appendUtf8String(appServiceInfo.getAppName() + "\t\'" + appServiceInfo.getAppKey() + "\'\t" + appServiceInfo.getAppSecret() + "\t" + appServiceInfo.getServiceId() + "\t" + appServiceInfo.isPassStsCheck()+"\r\n", file);
//        }
//    }
//
////    private static void write2ExcelFile(List<AppServiceInfo> appServiceInfos, String excelFileName){
////        String title = ExportExcelHeadEnum.APP_SERVICES_SHEET.getTitle();
////        String[] headers = ExportExcelHeadEnum.APP_SERVICES_SHEET.getHeaders();
////        String[] fields = ExportExcelHeadEnum.APP_SERVICES_SHEET.getFields();
////        XSSFWorkbook workbook = ExportExcelUtil.exportExcel(title, headers, fields, appServiceInfos);
////        ExportExcelUtil.writeFile(workbook, excelFileName);
////    }
//
//    private static List<AppServiceInfo> getAppServiceInfosFromFile(String fileName){
//        String json = FileUtil.readUtf8String(new File(fileName));
//        List<AppServiceInfo> appServiceInfos = JSON.parseArray(json, AppServiceInfo.class);
//        return appServiceInfos;
//    }
//
//    private static List<AppServiceInfo> queryFrogAppServiceInfos(String loginUrl, String queryAppUrl,String queryAppServiceUrl) {
//        CookieStore httpCookieStore = new BasicCookieStore();
//        try {
//            MockLoginUtil.mockLogin(loginUrl, LOGIN_ID, LOGIN_PWD, httpCookieStore);
//        } catch (Exception e1) {
//            log.info("登录失败");
//        }
//        Map<String, String> queryAppReq = buildQueryAppReq();
//        List<AppDO> apps = WedoctorCloudUtil.queryApps(queryAppUrl, queryAppReq, httpCookieStore);
//        List<AppServiceInfo> appServiceInfos = new ArrayList<>();
//        for (AppDO appDO : apps) {
//            Long id = appDO.getId();
//            Map<String, String> queryAppServiceReq = buildQueryAppServiceReq(id);
//            List<ServicesDO> appServices = WedoctorCloudUtil.queryAppServices(queryAppServiceUrl, queryAppServiceReq, httpCookieStore);
//            log.info("frog应用分配服务权限查询结果，appKey:{}, appSecret:{}, services数量：{}", appDO.getAppKey(), appDO.getAppSecret(), appServices.size());
//            List<String> serviceIds = appServices.stream().map(ServicesDO::getName).collect(Collectors.toList());
//            for (String serviceId : serviceIds){
//                AppServiceInfo appServiceInfo = new AppServiceInfo(appDO.getAppName(),appDO.getAppKey(),appDO.getAppSecret(),serviceId);
//                appServiceInfos.add(appServiceInfo);
//            }
//        }
//        log.info("frog应用分配服务权限查询结果，{}", JSON.toJSONString(appServiceInfos));
//        return appServiceInfos;
//    }
//
//
//    private static void stsCheckAuth(List<AppServiceInfo> appServiceInfos){
//        for (AppServiceInfo appServiceInfo : appServiceInfos){
//            Map<String, String> header = buildStsCheckHeader(appServiceInfo);
//            String resultJson = WedoctorCloudUtil.stsCheckAuth(ONLINE_STS_CHECK_AUTH_URL, header, null);
//            BaseResult<UserAccountSubApiVO> result = JSON.parseObject(resultJson, new TypeReference<BaseResult<UserAccountSubApiVO>>() {});
//            log.info("sts鉴权结果，appKey:{}, serviceId:{}", appServiceInfo.getAppKey(), appServiceInfo.getServiceId(), result.getCode());
//            appServiceInfo.setPassStsCheck("0".equals(result.getCode()) && appServiceInfo.getAppKey().equals(result.getData().getSignAccessKeyId()) && appServiceInfo.getAppSecret().equals(result.getData().getSignAccessSecret()));
//        }
//
//    }
//
//    public static Map<String, String> buildStsCheckHeader(AppServiceInfo appServiceInfo) {
//        Map<String, String> header = new HashMap<>();
//        header.put("accessKeyId",appServiceInfo.getAppKey());
//        header.put("apiIdentity",appServiceInfo.getServiceId());
//        header.put("productCode",ONLINE_PRODUCT_CODE);
//        header.put("traceId",UUID.randomUUID().toString());
//        return header;
//    }
//
//    public static Map<String, String> buildQueryAppReq() {
//        Map<String, String> queryReq = new HashMap<>();
//        queryReq.put("enabled", "0");
//        queryReq.put("page", "1");
//        queryReq.put("pageSize", "500");
//        return queryReq;
//    }
//
//    public static Map<String, String> buildQueryAppServiceReq(Long appId) {
//        Map<String, String> queryReq = new HashMap<>();
//        queryReq.put("id", String.valueOf(appId));
//        queryReq.put("status", "2");
//        queryReq.put("page", "1");
//        queryReq.put("pageSize", "1000");
//        return queryReq;
//    }
//}
