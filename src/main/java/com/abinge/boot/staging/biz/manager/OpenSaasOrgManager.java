package com.abinge.boot.staging.biz.manager;

import com.abinge.boot.staging.biz.model.config.DomainInfo;
import com.abinge.boot.staging.biz.model.config.OspEncologyConfiguration;
import com.abinge.boot.staging.biz.model.config.ServiceNoInfo;
import com.abinge.boot.staging.biz.utils.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Component
@Slf4j
public class OpenSaasOrgManager {
    @Resource
    OspEncologyConfiguration ospEncologyConfiguration;

    @Resource
    CloseableHttpClient httpClient;

//    public OrgInfoDTO queryOrgInfo(String serviceNo) {
//        try {
//            MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
//            queryParam.put("serviceNo", Arrays.asList(serviceNo));
//            String result = HttpUtil.sendGet(httpClient, ospEncologyConfiguration.getOpenSaasHost() + ospEncologyConfiguration.getOpenSaasOrgQueryBySubUrl(), null, null, queryParam);
//            log.info("[请求查询医院号信息], req={} res={} ", serviceNo, result);
//            ParamCheckUtil.checkBlank(result, ServicePipelineCode.SERVICE_NO_NOT_FUND);
//            Result<OrgInfoDTO> authRespResult = JSON.parseObject(result, new TypeReference<DefaultResult<OrgInfoDTO>>() {
//            });
//            ParamCheckUtil.checkFalse(!authRespResult.isSuccess() || Objects.isNull(authRespResult.getData()), ServicePipelineCode.SERVICE_NO_NOT_FUND);
//            return authRespResult.getData();
//        } catch (Exception e) {
//            log.error("[请求查询医院号信息]异常, serviceNo:{}", serviceNo, e);
//            ParamCheckUtil.buildFail(ServicePipelineCode.SERVICE_NO_NOT_FUND);
//        }
//        return null;
//    }

    /**
     * 根据域名查询医院号信息
     *
     * @param domain
     * @return
     */
    public DomainInfo queryServiceNoDomain(String domain) {
        try {
//            MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
//            queryParam.put("onlineStatus", Arrays.asList("1"));
//            queryParam.put("domainUrl", Arrays.asList(domain));
//            String result = HttpUtil.sendGet(httpClient, ospEncologyConfiguration.getOpenSaasHost() + ospEncologyConfiguration.getOpenSaasOrgQueryByDomainUrl(), null, null, queryParam);
//            log.info("[根据域名地址请求查询已上线医院号信息], req={} res={} ", domain, result);
//            ParamCheckUtil.checkBlank(result, ServicePipelineCode.DOMAIN_NOT_FOUND);
//            Result<OrgServiceDTO> authRespResult = JSON.parseObject(result, new TypeReference<DefaultResult<OrgServiceDTO>>() {
//            });
//            OrgServiceDTO orgServiceDTO = authRespResult.getData();
//            ParamCheckUtil.checkFalse(!authRespResult.isSuccess() || Objects.isNull(orgServiceDTO), "根据域名地址请求查询已上线医院号信息为空");
//
//            ServiceNoInfo serviceNoInfo = queryServiceNoInfo(orgServiceDTO.getServiceNo());
//            AccessInfo accessInfo = serviceNoInfo.getAccessInfo();
//            boolean hasAccess = Objects.isNull(accessInfo);
//            // 患者端统一为校验登录，用户类型为0-患者端
//            DomainInfo domainInfo = DomainInfo.builder()
//                    .serviceNo(orgServiceDTO.getServiceNo())
//                    .appKey(hasAccess ? null : accessInfo.getAppKey())
//                    .appSecret(hasAccess ? null : accessInfo.getAppSecret())
//                    .host(StringUtils.isEmpty(orgServiceDTO.getCustomUrl()) ? orgServiceDTO.getDefaultUrl() : orgServiceDTO.getCustomUrl())
//                    .loginAuth(Boolean.TRUE)
//                    .userType(0)
//                    .build();
//            return domainInfo;
        } catch (Exception e) {
            log.error("[根据域名地址请求查询已上线医院号信息]异常, domain：{}", domain, e);
//            ParamCheckUtil.buildFail(ServicePipelineCode.DOMAIN_NOT_FOUND);
        }
        return null;
    }

    public ServiceNoInfo queryServiceNoInfo(String serviceNo) {
        try {
//            MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
//            queryParam.put("serviceNo", Arrays.asList(serviceNo));
//            String result = HttpUtil.sendGet(httpClient, ospEncologyConfiguration.getOpenSaasHost() + ospEncologyConfiguration.getOpenSaasQueryServiceNoAuthInfoUrl(), null, null, queryParam);
//            log.info("[请求查询已上线医院号信息], req={} res={} ", serviceNo, result);
//            ParamCheckUtil.checkBlank(result, ServicePipelineCode.SERVICE_NO_NOT_FUND);
//            Result<OrgServiceDetailRespDTO> authRespResult = JSON.parseObject(result, new TypeReference<DefaultResult<OrgServiceDetailRespDTO>>() {
//            });
//            OrgServiceDetailRespDTO serviceDetail = authRespResult.getData();
//            ParamCheckUtil.checkFalse(!authRespResult.isSuccess() || Objects.isNull(serviceDetail), authRespResult.getMessage());
//
//            List<AppAccessRespDTO> appAccessList = serviceDetail.getAppAccessList();
//            return ServiceNoInfo.builder()
//                    .serviceNo(serviceDetail.getServiceNo())
//                    .orgCode(serviceDetail.getOrgCode())
//                    .domainHost(serviceDetail.getDomainUrl())
//                    .accessInfo(StringUtils.isEmpty(appAccessList) ? null : BeanUtil.convert(appAccessList.get(0), AccessInfo.class))
//                    .accessMap(EncologyTransUtil.transformAccessMap(appAccessList))
//                    .resourceMap(EncologyTransUtil.transformResourceMap(serviceDetail.getAppResourceList()))
//                    .serviceNoConfigs(EncologyTransUtil.transformServiceNoConfigs(serviceDetail.getOrgServiceConfigList()))
//                    .build();
        } catch (Exception e) {
            log.error("[请求查询已上线医院号信息]异常, serviceNo：{}", serviceNo, e);
//            ParamCheckUtil.buildFail(ServicePipelineCode.SERVICE_NO_NOT_FUND);
        }
        return null;
    }
}
