package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointReasonCategory implements CommonCode {
    ISSUE("I", "발행"),
    BURN("B", "소멸"),
    TRANSFER("T", "전송"),
    EXCHANGE("E", "교환");

    private String code;
    private String desc;
}
