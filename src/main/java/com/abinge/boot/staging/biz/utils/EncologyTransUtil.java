package com.abinge.boot.staging.biz.utils;

import com.google.common.collect.Maps;
import com.guahao.opensaas.ecology.common.util.BeanUtil;
import com.guahao.opensaas.ecology.share.app.dto.Resp.AppAccessRespDTO;
import com.guahao.opensaas.ecology.share.app.dto.Resp.AppResourceRespDTO;
import com.guahao.opensaas.ecology.share.app.dto.Resp.OrgServiceConfigRespDTO;
import com.guahao.opensaas.gateway.biz.model.config.AccessInfo;
import com.guahao.opensaas.gateway.biz.model.config.ResourceInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

public class EncologyTransUtil {
    public static Map<String, AccessInfo> transformAccessMap(List<AppAccessRespDTO> accessRespDTOList) {
        if (CollectionUtils.isEmpty(accessRespDTOList)) {
            return Maps.newHashMap();
        }
        Map<String, AccessInfo> result = Maps.newHashMap();
        for (AppAccessRespDTO appAccess : accessRespDTOList) {
            result.put(appAccess.getAppKey(), BeanUtil.convert(appAccess, AccessInfo.class));
        }
        return result;
    }

    public static Map<String, ResourceInfo> transformResourceMap(List<AppResourceRespDTO> appResourceList) {
        if (CollectionUtils.isEmpty(appResourceList)) {
            return Maps.newHashMap();
        }
        Map<String, ResourceInfo> result = Maps.newHashMap();
        for (AppResourceRespDTO appResource : appResourceList) {
            result.put(appResource.getResourceCode(), BeanUtil.convert(appResource, ResourceInfo.class));
        }
        return result;
    }

    public static  Map<String, String> transformServiceNoConfigs(List<OrgServiceConfigRespDTO> orgServiceConfigList) {
        if (CollectionUtils.isEmpty(orgServiceConfigList)) {
            return Maps.newHashMap();
        }
        Map<String, String> result = Maps.newHashMap();
        for (OrgServiceConfigRespDTO orgConfig : orgServiceConfigList) {
            result.put(orgConfig.getConfigKey(), orgConfig.getConfigValue());
        }
        return result;
    }
}
