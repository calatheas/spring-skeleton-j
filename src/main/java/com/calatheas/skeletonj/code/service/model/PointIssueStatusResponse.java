package com.calatheas.skeletonj.code.service.model;

import com.calatheas.skeletonj.common.code.CommonCode;
import com.calatheas.skeletonj.common.code.PointProgressStatus;
import com.calatheas.skeletonj.common.code.PointProgressStatusCategory;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PointIssueStatusResponse extends BaseCode {

    private PointProgressStatusCategory category;

    private PointIssueStatusResponse(CommonCode commonCode) {
        super(commonCode);

        if (commonCode instanceof PointProgressStatus) {
            category = ((PointProgressStatus) commonCode).getCategory();
        }
    }

    public static PointIssueStatusResponse of(CommonCode commonCode) {
        return new PointIssueStatusResponse(commonCode);
    }
}
