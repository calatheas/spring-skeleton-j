package com.calatheas.skeletonj.admin.config;

import com.calatheas.skeletonj.common.interceptor.ApiAuthorizationInterceptor;
import com.calatheas.skeletonj.common.model.GlobalSession;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class AdminClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(ApiAuthorizationInterceptor.PARTNER_NID_HEADER_KEY, GlobalSession.getPartnerNid().toString());
            requestTemplate.header(ApiAuthorizationInterceptor.SESSION_ID_HEADER_KEY, GlobalSession.getSessionId());
        };
    }
}
