package com.calatheas.skeletonj.common.constant;

import java.util.List;

public class AuthorizationInterceptorPath {
    public static final List<String> API_PATH_PATTERNS = List.of("/api/**");
    public static final List<String> OPEN_API_PATH_PATTERNS = List.of("/openapi/**");

    public static final List<String> EXCLUDE_API_PATH_PATTERNS = List.of(
            "/**/sample/**",
            "/callback/**"
    );
}
