//package com.abinge.boot.staging.wedoctor;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.poi.excel.ExcelUtil;
//import cn.hutool.poi.excel.ExcelWriter;
//import com.abinge.boot.staging.utils.MockLoginUtil;
//import com.abinge.boot.staging.utils.WedoctorCloudUtil;
//import com.alibaba.fastjson.JSON;
//import com.guahao.convention.data.domain.Page;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.http.client.CookieStore;
//import org.apache.http.impl.client.BasicCookieStore;
//
//import java.io.File;
//import java.util.*;
//
//@Slf4j
//public class WedoctorGopsTest {
//    private static final String ONLINE_LOGIN_URL = "http://user.gops.guahao.cn/dologin";
//    private static final String ONLINE_QUERY_APP_URL = "http://ryyun.gops.guahao.cn/platform/user_account_sub/query_page";
//    private static final String ONLINE_QUERY_APP_DETAIL_URL = "http://ryyun.gops.guahao.cn/platform/user_account_sub/detail?accountSubUid=";
//    private static final String ONLINE_QUERY_ROLE_URL = "http://ryyun.gops.guahao.cn/platform/user_role_predefined/query_related_page";
//    private static final String ONLINE_QUERY_ROLE_API_URL = "http://ryyun.gops.guahao.cn/platform/user_role/query_existed_api?roleId=";
//    private static final String LOGIN_ID = "chenbj";
//    private static final String LOGIN_PWD = "HXcbj0524@1";
//    private static final String USER_ACCOUNT_ID = "1781464937561071616";
//
//
//    public static void main(String[] args) {
////        List<UserAccountSubVO> userAccountSubVOS = queryUserAccountSubAppServiceInfos();
////        write2File(userAccountSubVOS, "20210927_子应用权限-1.json");
//        String json = FileUtil.readUtf8String(new File("20210927_子应用权限-1.json"));
//        List<UserAccountSubVO> userAccountSubVOS = JSON.parseArray(json, UserAccountSubVO.class);
//        List<Map<String,String>> rows = new ArrayList<>(userAccountSubVOS.size());
//        for (UserAccountSubVO userAccountSubVO : userAccountSubVOS){
//            Map<String,String> row = new LinkedHashMap<>();
//            row.put("外部机构",userAccountSubVO.getNickName());
//            row.put("需求背景",userAccountSubVO.getUserRemark());
//            row.put("申请数据范围",dealApiList(userAccountSubVO.getApiSimpleList()));
//            row.put("申请数据内容说明",userAccountSubVO.getUserRemark());
//            row.put("申请部门",userAccountSubVO.getUserRemark());
//            row.put("申请人",userAccountSubVO.getUserRemark());
//            row.put("申请日期", DateUtil.format(userAccountSubVO.getGmtCreated(), "YYYY-MM-HH"));
//            rows.add(row);
//        }
//        writeExcel(rows,"D:\\private\\edu\\abing-boot\\20210927_子应用权限v1.xlsx");
//
//    }
//
//    private static String dealApiList(List<RoleApiSimpleVO> apiSimpleList){
//        if (CollectionUtils.isEmpty(apiSimpleList)){
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (RoleApiSimpleVO apiSimpleVO : apiSimpleList){
//            sb.append(apiSimpleVO.getServiceName()).append("-").append(apiSimpleVO.getServiceId()).append("\n");
//        }
//        return sb.toString();
//    }
//
//    private static void writeExcel(List<Map<String, String>> rows, String fileName) {
//        // 通过工具类创建writer
//        ExcelWriter writer = ExcelUtil.getWriter(fileName);
//        // 一次性写出内容，使用默认样式，强制输出标题
//        writer.write(rows, true);
//        // 关闭writer，释放内存
//        writer.close();
//    }
//
//    private static List<UserAccountSubVO> queryUserAccountSubAppServiceInfos() {
//        CookieStore httpCookieStore = new BasicCookieStore();
//        try {
//            MockLoginUtil.mockLogin(ONLINE_LOGIN_URL, LOGIN_ID, LOGIN_PWD, httpCookieStore);
//        } catch (Exception e1) {
//            log.info("登录失败");
//        }
//        String queryAppReq = buildQueryAppReq();
//        Page<UserAccountSubVO> userAccountSubVOPage = WedoctorCloudUtil.queryUserAccountSub(ONLINE_QUERY_APP_URL, queryAppReq, httpCookieStore);
//        List<UserAccountSubVO> results = userAccountSubVOPage.getResults();
//        for (UserAccountSubVO appDO : userAccountSubVOPage.getResults()) {
//            String accountSubUid = appDO.getAccountSubUid();
//            String queryAppServiceReq = buildQueryAppServiceReq(accountSubUid);
//            Page<RoleSimpleVO> appServices = WedoctorCloudUtil.queryUserAccountSubRole(ONLINE_QUERY_ROLE_URL, queryAppServiceReq, httpCookieStore);
//            List<RoleApiSimpleVO> apiSimpleList = new ArrayList<>();
//            for (RoleSimpleVO roleSimpleVO : appServices.getResults()) {
//                Long roleId = roleSimpleVO.getRoleId();
//                List<RoleApiSimpleVO> roleApiSimpleVOS = WedoctorCloudUtil.queryRoleServices(ONLINE_QUERY_ROLE_API_URL + roleId, null, httpCookieStore);
//                apiSimpleList.addAll(roleApiSimpleVOS);
//            }
//            appDO.setApiSimpleList(apiSimpleList);
//        }
//        log.info("应用分配服务权限查询结果，{}", JSON.toJSONString(results));
//        return results;
//    }
//
//    private static String buildQueryAppReq() {
//        Map<String, Object> req = new HashMap<>();
//        req.put("currentUid", USER_ACCOUNT_ID);
//        req.put("pageNumber", 2);
//        req.put("pageSize", 200);
//        return JSON.toJSONString(req);
//    }
//
//    private static String buildQueryAppServiceReq(String accountSubUid) {
//        Map<String, Object> req = new HashMap<>();
//        req.put("accountSubUid", accountSubUid);
//        req.put("currentUid", USER_ACCOUNT_ID);
//        req.put("pageNumber", 1);
//        req.put("pageSize", 200);
//        return JSON.toJSONString(req);
//    }
//
//    private static void write2File(List<UserAccountSubVO> userAccountSubVOS, String fileName) {
//        File file = new File(fileName);
//        FileUtil.appendUtf8String(JSON.toJSONString(userAccountSubVOS), file);
////        for (AppServiceInfo appServiceInfo : appServiceInfos) {
////            FileUtil.appendUtf8String(appServiceInfo.getAppName() + "\t\'" + appServiceInfo.getAppKey() + "\'\t" + appServiceInfo.getAppSecret() + "\t" + appServiceInfo.getServiceId() + "\t" + appServiceInfo.isPassStsCheck()+"\r\n", file);
////        }
//    }
//
//}
