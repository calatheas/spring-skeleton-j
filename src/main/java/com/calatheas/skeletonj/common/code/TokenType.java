package com.calatheas.skeletonj.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType implements CommonCode {
    BEARER("bearer", "Bearer token");

    private String code;
    private String desc;
}
