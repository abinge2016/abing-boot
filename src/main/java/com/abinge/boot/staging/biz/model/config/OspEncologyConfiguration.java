package com.abinge.boot.staging.biz.model.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * openSaas-ecology 核心服务相关路径
 * 用于网关加载和缓存相关核心数据使用
 *
 * @author chenbj
 */
@Data
@Configuration
public class OspEncologyConfiguration {
    @Value("${guahao.opensaas.ecology.host}")
    private String openSaasHost;

    @Value("${guahao.opensaas.ecology.auth.url:/osp/auth/check_auth}")
    private String openSaasAuthUrl;

    @Value("${guahao.opensaas.ecology.user.query-by-token:/osp/user/verify_token}")
    private String openSaasUserQueryByTokenUrl;

    @Value("${guahao.opensaas.ecology.user.query-by-openid:/osp/user/get_userprofile_by_openid}")
    private String openSaasUserQueryByOpenUserIdUrl;

    @Value("${guahao.opensaas.ecology.org.query-by-service-no:/osp/org/serviceno/get_org_detail_by_service_no}")
    private String openSaasOrgQueryBySubUrl;

    @Value("${guahao.opensaas.ecology.org.query-by-domain-url:/osp/org/serviceno/get_service_no_by_domain_url}")
    private String openSaasOrgQueryByDomainUrl;

    @Value("${guahao.opensaas.ecology.org.query-auth-by-service-no:/osp/auth/query_service_no_auth_info}")
    private String openSaasQueryServiceNoAuthInfoUrl;

    @Value("${guahao.opensaas.ecology.access.query:/osp/access/query_access_by_app_key}")
    private String openSaasQueryAccessUrl;

    @Value("${guahao.opensaas.ecology.app.query:/osp/app/query}")
    private String openSaasQueryAppUrl;

    @Value("${guahao.opensaas.ecology.auth.code:/osp/user_third_auth/code}")
    private String openSaasAuthCodeUrl;

    @Value("${guahao.opensaas.ecology.auth.token:/osp/user_third_auth/access_token}")
    private String openSaasAuthTokenUrl;

    @Value("${guahao.opensaas.ecology.cache.delete:/osp/user_third_auth/delete_db_cache}")
    private String openSaasCacheDeleteUrl;

    @Value("${guahao.opensaas.ecology.cache.query:/osp/user_third_auth/query_db_current_valid_caches}")
    private String openSaasCurrentValidCacheQueryUrl;

    @Value("${guahao.opensaas.ecology.user.login-by-pwd:/api/user/loginByPwd}")
    private String openSaasUserLoginUrl;

    @Value("${guahao.opensaas.ecology.user.logout:/api/user/logout}")
    private String openSaasUserLogoutUrl;

    @Value("${guahao.opensaas.ecology.user.get_user_profile_by_token}")
    private String openSaasUserProfileByTokenUrl;

    @Value("${guahao.opensaas.ecology.user.login_doctor_by_bind:/api/user/login_doctor_by_bind}")
    private String openSaasUserLoginDoctorByBind;
}
