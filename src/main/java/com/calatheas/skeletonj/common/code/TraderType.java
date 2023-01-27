package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum TraderType implements CommonCode {

    SENDER("SENDER", "보내는 이"),
    RECIPIENT("RECIPIENT", "받는 이");


    private String code;
    private String desc;
}
