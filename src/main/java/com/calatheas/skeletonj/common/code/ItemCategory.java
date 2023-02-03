package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum ItemCategory implements CommonCode {
    APPLE("A", "애플"),
    SAMSUNG("S", "삼성");

    private String code;
    private String desc;
}
