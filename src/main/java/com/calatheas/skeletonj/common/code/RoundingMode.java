package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum RoundingMode implements CommonCode {
    UP("0", "올림", java.math.RoundingMode.UP),
    DOWN("1", "내림", java.math.RoundingMode.DOWN),
    HALF_UP("4", "반올림", java.math.RoundingMode.HALF_UP);

    private String code;
    private String desc;
    private java.math.RoundingMode roundingMode;
}
