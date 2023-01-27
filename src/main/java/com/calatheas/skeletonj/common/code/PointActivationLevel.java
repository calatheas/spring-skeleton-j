package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 포인트 활성화 레벨
 * 1. 생성 : 발행 요청에 의해 생성
 * 2. 사용가능 : 온체인 발행 완료 이벤트에 의해 사용가능으로 변경
 * 3. 일부사용 : 정산(포인트 잔액변경) 에 의해 변경 (해당 포인트가 당일 결제액, 소멸액 중 일부 포함된 경우)
 * 4. 전부사용 : 정산(포인트 잔액변경) 에 의해 변경 (해당 포인트가 당일 결제액, 소멸액보다 작아서 전부 포함된 경우)
 */
@Getter
@AllArgsConstructor
@CodeClass
public enum PointActivationLevel implements CommonCode {
    CREATED("C1", "생성"),
    AVAILABLE("A1", "사용가능"),
    USED_PARTIALLY("U1", "일부사용"),
    USED_ALL("U2", "전부사용"),
    EXPIRED("E1", "유효기간만료");

    private String code;
    private String desc;
}
