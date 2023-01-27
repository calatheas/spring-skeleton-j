package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.code.service.model.PointIssueStatusResponse;
import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@CodeClass(value = PointIssueStatusResponse.class)
public enum PointProgressStatus implements CommonCode {

    REQUEST("R001", "요청접수", PointProgressStatusCategory.REQUEST, Yn.N, Yn.N),
    IN_PROGRESS("R002", "요청중", PointProgressStatusCategory.REQUEST, Yn.Y, Yn.N),
    APPROVAL("A001", "승인 처리", PointProgressStatusCategory.APPROVAL, Yn.N, Yn.Y),
    NOT_ENOUGH_BANKING("A002", "계좌 잔액 부족", PointProgressStatusCategory.APPROVAL, Yn.N, Yn.N),
    NOT_ENOUGH_POINT("A003", "포인트 잔액 부족", PointProgressStatusCategory.APPROVAL, Yn.N, Yn.N),
    ONCHAIN_REQUEST_FAILURE("A004", "블록체인 접수 실패", PointProgressStatusCategory.APPROVAL, Yn.N, Yn.N),
    APPROVAL_FAILURE("A005", "승인 실패", PointProgressStatusCategory.APPROVAL, Yn.N, Yn.N),
    ONCHAIN_IN_PROGRESS("O001", "블록체인 기록 중", PointProgressStatusCategory.ONCHAIN, Yn.Y, Yn.N),
    ONCHAIN_FAILURE("O002", "블록체인 기록 실패", PointProgressStatusCategory.ONCHAIN, Yn.N, Yn.N),
    SUCCESS("S000", "전체 성공", PointProgressStatusCategory.SUCCESS, Yn.N, Yn.Y),
    BANKING_SUCCESS("B000", "이체 성공", PointProgressStatusCategory.BANKING, Yn.N, Yn.Y),
    BANKING_FAILURE("B001", "이체 실패", PointProgressStatusCategory.BANKING, Yn.N, Yn.N);

    private String code;
    private String desc;
    private PointProgressStatusCategory category;

    private Yn inProgressYn;
    private Yn successYn;

    public static PointProgressStatus ofPointTransaction(@NonNull PointRequestStatus status, @NonNull PointTransactionStatus pointTransactionStatus, @NonNull PointTransactionType pointTransactionType) {
        if (status == PointRequestStatus.SUCCESS) {
            switch (pointTransactionStatus) {
                case CREATED:
                    return PointProgressStatus.ONCHAIN_IN_PROGRESS;
                case SUCCESS:
                    if (pointTransactionType == PointTransactionType.BANK) {
                        return PointProgressStatus.BANKING_SUCCESS;
                    } else {
                        return PointProgressStatus.SUCCESS;
                    }
                case FAILURE:
                    if (pointTransactionType == PointTransactionType.ONCHAIN) {
                        return PointProgressStatus.ONCHAIN_FAILURE;
                    } else if (pointTransactionType == PointTransactionType.BANK) {
                        return PointProgressStatus.BANKING_FAILURE;
                    }
            }
        }
        return PointProgressStatus.APPROVAL_FAILURE;
    }

}
