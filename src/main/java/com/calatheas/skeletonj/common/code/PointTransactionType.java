package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointTransactionType implements CommonCode {
    BANK("BANK", "은행이체"),
    ONCHAIN("ONCHAIN","블록체인기록");

    private String code;
    private String desc;
}
