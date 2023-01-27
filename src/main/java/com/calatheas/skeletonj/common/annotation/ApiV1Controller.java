package com.calatheas.skeletonj.common.annotation;

import com.calatheas.skeletonj.common.constant.ApiControllerConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(ApiControllerConstants.API_V1)
public @interface ApiV1Controller {
}
