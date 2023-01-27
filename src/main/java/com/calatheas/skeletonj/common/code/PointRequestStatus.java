package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointRequestStatus implements CommonCode {

    REQUEST("PR01", "요청접수", true, PointProgressStatus.REQUEST),
    IN_PROGRESS("PR02", "기록중", false, PointProgressStatus.IN_PROGRESS),
    SUCCESS("PR03", "정상", false, PointProgressStatus.APPROVAL),
    FAILURE("PR04", "실패", true, PointProgressStatus.APPROVAL_FAILURE),
    NOT_ENOUGH_BANKING("PR05", "근거자산 잔액부족", true, PointProgressStatus.NOT_ENOUGH_BANKING),
    NOT_ENOUGH_POINT("PR06", "포인트 잔액부족", true, PointProgressStatus.NOT_ENOUGH_POINT),
    FAILURE_ONCHAIN("PR07", "블록체인 접수실패", true, PointProgressStatus.ONCHAIN_REQUEST_FAILURE),
    ;

    private String code;
    private String desc;
    private boolean isRetryable;
    private PointProgressStatus pointProgressStatus;

    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
