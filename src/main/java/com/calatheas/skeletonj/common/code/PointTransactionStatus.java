package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointTransactionStatus implements CommonCode {
    CREATED("CREATED", "생성"),
    SUCCESS("SUCCESS", "성공"),
    FAILURE("FAILURE", "실패");

    private String code;
    private String desc;
}
