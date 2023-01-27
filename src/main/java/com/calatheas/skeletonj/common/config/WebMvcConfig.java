package com.calatheas.skeletonj.common.config;

import com.calatheas.skeletonj.common.constant.AuthorizationInterceptorPath;
import com.calatheas.skeletonj.common.converter.LocalDateFormatter;
import com.calatheas.skeletonj.common.converter.LocalDateTimeFormatter;
import com.calatheas.skeletonj.common.converter.StringToEnumConverterFactory;
import com.calatheas.skeletonj.common.filter.CustomLoggingFilter;
import com.calatheas.skeletonj.common.interceptor.ApiAuthorizationInterceptor;
import com.calatheas.skeletonj.common.interceptor.OpenApiAuthorizationInterceptor;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

import static com.calatheas.skeletonj.common.constant.ConstantBank.LOCAL_DATE_FORMAT;
import static com.calatheas.skeletonj.common.constant.ConstantBank.LOCAL_DATE_TIME_FORMAT;

@Configuration
@EnableJpaAuditing
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Autowired
    @Lazy
    private ApiAuthorizationInterceptor apiAuthorizationInterceptor;

    @Autowired
    @Lazy
    private OpenApiAuthorizationInterceptor openApiAuthorizationInterceptor;

    @Autowired
    private MessageSource messageSource;

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    public CustomLoggingFilter requestPreFilter() {
        return new CustomLoggingFilter(profile);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiAuthorizationInterceptor)
                .addPathPatterns(AuthorizationInterceptorPath.API_PATH_PATTERNS)
                .excludePathPatterns(AuthorizationInterceptorPath.EXCLUDE_API_PATH_PATTERNS);

        registry.addInterceptor(openApiAuthorizationInterceptor)
                .addPathPatterns(AuthorizationInterceptorPath.OPEN_API_PATH_PATTERNS);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
        registry.addFormatter(new LocalDateTimeFormatter());
        registry.addFormatter(new LocalDateFormatter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(86400);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(LOCAL_DATE_TIME_FORMAT);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT)));
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT)));
        };
    }
}
