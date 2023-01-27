package com.calatheas.skeletonj.code.service.model;

import com.calatheas.skeletonj.common.code.CommonCode;
import com.calatheas.skeletonj.common.code.PointReasonCategory;
import com.calatheas.skeletonj.common.code.PointReasonCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PointReasonCodeResponse extends BaseCode {
    private PointReasonCategory category;

    private PointReasonCodeResponse(CommonCode commonCode) {
        super(commonCode);

        if (commonCode instanceof PointReasonCode) {
            category = ((PointReasonCode) commonCode).getCategory();
        }
    }

    public static PointReasonCodeResponse of(CommonCode commonCode) {
        return new PointReasonCodeResponse(commonCode);
    }
}
