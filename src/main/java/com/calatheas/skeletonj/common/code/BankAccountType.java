package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum BankAccountType implements CommonCode {

    NORMAL("NORMAL", "일반자산"),
    MAIN("MAIN", "대표자산");

    private String code;
    private String desc;
}
