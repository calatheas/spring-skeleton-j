package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PartnerType implements CommonCode {
    POINT_ISSUE("PI", "포인트발행처"),
    POINT_USE("PU", "포인트사용처");

    private String code;
    private String desc;
}
