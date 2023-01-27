package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointUsageType implements CommonCode {
    ISSUE("01", "발행 가능"),
    USE("02", "사용 가능");

    private String code;
    private String desc;
}
