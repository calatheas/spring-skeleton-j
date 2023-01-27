package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.code.service.model.PointReasonCodeResponse;
import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass(value = PointReasonCodeResponse.class)
public enum PointReasonCode implements CommonCode {
    ISSUE_AUTO("I01", "발행-자동", PointReasonCategory.ISSUE),
    ISSUE_MANUAL("I02", "발행-수동", PointReasonCategory.ISSUE),
    BURN_CASH("B01", "소멸-현금환불", PointReasonCategory.BURN),
    BURN_CANCEL("B02", "소멸-발행취소", PointReasonCategory.BURN),
    BURN_EXPIRE("B03", "소멸-유효기간만료", PointReasonCategory.BURN),
    TRANSFER_PAY("T01", "전송-결제", PointReasonCategory.TRANSFER),
    TRANSFER_PAY_CANCEL("T02", "전송-결제취소", PointReasonCategory.TRANSFER),
    TRANSFER_SETTLE_CUSTOMER("T03", "전송-고객정산", PointReasonCategory.TRANSFER),
    TRANSFER_SETTLE_PARTNER("T04", "전송-제휴사정산", PointReasonCategory.TRANSFER);

    private String code;
    private String desc;
    private PointReasonCategory category;

    public boolean isIssue() {
        return category == PointReasonCategory.ISSUE;
    }

    public boolean isBurn() {
        return category == PointReasonCategory.BURN;
    }

    public boolean isPay() {
        return this == TRANSFER_PAY || this == TRANSFER_PAY_CANCEL;
    }

    public boolean isSettle() {
        return this == TRANSFER_SETTLE_CUSTOMER || this == TRANSFER_SETTLE_PARTNER;
    }
}
