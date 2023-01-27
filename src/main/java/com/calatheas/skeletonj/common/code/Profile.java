package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum Profile {
    DEFAULT(CONST.DEFAULT, true, true),
    DEV(CONST.DEV, false, true),
    STG(CONST.STG, false, false),
    PRD(CONST.PRD, false, false);

    private String code;
    private boolean isLocalEnvironment;
    private boolean isDevEnvironment; // 넓은 범위의 개발(로컬, 개발, 검증X, 운영X)

    public static Profile fromCode(String code) {
        for (Profile profile : Profile.values()) {
            if (profile.getCode().equals(code)) {
                return Profile.valueOf(profile.name());
            }
        }
        return Profile.valueOf(code);
    }

    public static class CONST {
        public static final String DEFAULT = "default";
        public static final String DEV = "dev";
        public static final String STG = "stg";
        public static final String PRD = "prd";
    }
}
