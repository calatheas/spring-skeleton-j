package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum BankRequestReasonType implements CommonCode {
    REFUND("REFUND", "환불"),
    SETTLEMENT("SETTLE", "정산"),
    HAND_INPUT("HAND_INP","수기입력");

    private String code;
    private String desc;
}
