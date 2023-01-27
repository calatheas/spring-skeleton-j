package com.calatheas.skeletonj.code.service.model;

import com.calatheas.skeletonj.common.code.CommonCode;
import com.calatheas.skeletonj.common.code.PointType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PointTypeResponse extends BaseCode {
    private boolean payable;
    private boolean needSettlementPoint;
    private boolean refundable;

    private PointTypeResponse(CommonCode commonCode) {
        super(commonCode);

        if (commonCode instanceof PointType) {
            payable = ((PointType) commonCode).isPayable();
            needSettlementPoint = ((PointType) commonCode).isNeedSettlementPoint();
            refundable = ((PointType) commonCode).isRefundable();
        }
    }

    public static PointTypeResponse of(CommonCode commonCode) {
        return new PointTypeResponse(commonCode);
    }
}
