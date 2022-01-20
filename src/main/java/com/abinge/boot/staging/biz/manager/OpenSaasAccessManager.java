package com.abinge.boot.staging.biz.manager;

import com.abinge.boot.staging.biz.constants.ServicePipelineCode;
import com.abinge.boot.staging.biz.model.config.AccessInfo;
import com.abinge.boot.staging.biz.model.config.AppInfo;
import com.abinge.boot.staging.biz.model.config.OspEncologyConfiguration;
import com.abinge.boot.staging.biz.model.config.Result;
import com.abinge.boot.staging.biz.utils.HttpUtil;
import com.abinge.boot.staging.biz.utils.ParamCheckUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

@Component
@Slf4j
public class OpenSaasAccessManager {
    @Resource
    OspEncologyConfiguration ospEncologyConfiguration;

    @Resource
    CloseableHttpClient httpClient;

    public AccessInfo queryAccessInfo(String appKey) {
        try {
            MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
            queryParam.put("appKey", Arrays.asList(appKey));
            String result = HttpUtil.sendGet(httpClient, ospEncologyConfiguration.getOpenSaasHost() + ospEncologyConfiguration.getOpenSaasQueryAccessUrl(), null, null, queryParam);
            log.info("[请求查询访问凭证信息], req={} res={} ", appKey, result);
            ParamCheckUtil.checkBlank(result, ServicePipelineCode.ACCESS_NOT_FOUND);
            Result<Objects> authRespResult = JSON.parseObject(result, new TypeReference<Result>() {
            });
            ParamCheckUtil.checkFalse(!authRespResult.isSuccess() || Objects.isNull(authRespResult.getData()), ServicePipelineCode.ACCESS_NOT_FOUND);
//            return BeanUtil.convert(authRespResult.getData(), AccessInfo.class);
        } catch (Exception e) {
            log.error("[请求查询访问凭证信息]异常, appkey:{}", appKey, e);
            ParamCheckUtil.buildFail(ServicePipelineCode.ACCESS_NOT_FOUND);
        }
        return null;
    }

    public AppInfo queryAppInfo(String appCode) {
        try {
//            AppQueryReqDTO appQueryDTO = new AppQueryReqDTO();
//            appQueryDTO.setAppCode(appCode);
//            String req = JSON.toJSONString(appQueryDTO);
//            String result = HttpUtil.sendPostJSON(httpClient, ospEncologyConfiguration.getOpenSaasHost() + ospEncologyConfiguration.getOpenSaasQueryAppUrl(), null, req);
//            log.info("[请求查询ISV应用信息], req={} res={} ", req, result);
//            ParamCheckUtil.checkBlank(result, ServicePipelineCode.APP_NOT_FOUND);
//            Result<AppDetailRespDTO> authRespResult = JSON.parseObject(result, new TypeReference<DefaultResult<AppDetailRespDTO>>() {
//            });
//            ParamCheckUtil.checkFalse(!authRespResult.isSuccess() || Objects.isNull(authRespResult.getData()), ServicePipelineCode.ACCESS_NOT_FOUND);
//            AppDetailRespDTO appDetail = authRespResult.getData();
//            return AppInfo.builder()
//                    .appCode(appDetail.getAppCode())
//                    .appStatus(appDetail.getAppStatus())
//                    .loginUrl(appDetail.getLoginUrl())
//                    .messageUrl(appDetail.getMessageUrl())
//                    .accessMap(EncologyTransUtil.transformAccessMap(appDetail.getAppAccessList()))
//                    .build();
        } catch (Exception e) {
            log.error("[请求查询ISV应用信息]异常, appCode:{}", appCode, e);
            ParamCheckUtil.buildFail(ServicePipelineCode.APP_NOT_FOUND);
        }
        return null;
    }
}
