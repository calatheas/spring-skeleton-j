package com.calatheas.skeletonj.auth.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class AuthClientConfig {
    private static final String CUSTOM_KEY = "x-custom-key";

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(CUSTOM_KEY, "");
        };
    }
}
