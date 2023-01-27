package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum SettlementStatus implements CommonCode {

    RESERVED("SS01", "정산준비"),
    IN_PROGRESS("SS02", "정산중"),
    SUCCESS("SS03", "정산완료"),
    FAILURE("SS04", "정산실패");

    private String code;
    private String desc;

}
