package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointWalletStatus implements CommonCode {
    READY("READY", "준비중"),
    ABNORMAL("ABNORMAL", "확인필요");

    private String code;
    private String desc;
}
