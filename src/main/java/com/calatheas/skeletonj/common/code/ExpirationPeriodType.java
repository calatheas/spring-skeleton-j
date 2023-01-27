package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum ExpirationPeriodType implements CommonCode {
    YEAR("YEAR", "년"),
    MONTH("MONTH", "월"),
    DAY("DAY", "일"),
    DATE("DATE", "만료일자");

    private String code;
    private String desc;
}
