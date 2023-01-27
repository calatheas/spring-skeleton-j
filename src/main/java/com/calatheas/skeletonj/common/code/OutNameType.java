package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum OutNameType implements CommonCode {

    HOLDER_NAME("A", "예금주"),
    ISSUE_NID("B", "거래번호"),
    CODE("C", "지정코드"),
    CODE_AND_HOLDER_NAME("D", "지정코드+예금주"),
    CODE_AND_ISSUE_NID("E", "지정코드+거래번호")
    ;

    private String code;
    private String desc;
}
