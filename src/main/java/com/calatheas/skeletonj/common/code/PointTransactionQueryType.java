package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointTransactionQueryType implements CommonCode {

    ISSUE("ISSUE", "포인트발행/소멸"),
    TRANSFER("TRANSFER", "포인트전송");

    private String code;
    private String desc;
}
