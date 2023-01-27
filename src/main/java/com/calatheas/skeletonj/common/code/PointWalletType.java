package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointWalletType implements CommonCode {
    PARTNER("PARTNER", "제휴사"),
    CUSTOMER("CUSTOMER", "고객");

    private String code;
    private String desc;
}
