package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 플랫폼(partner)이 고객(customer) 또는 사용처(partner)에게 정산 항목을 분류하기 위한 유형
 * 1. 지급액 : 정산 대상에게 지급되는 항목의 유형
 * 2. 수수료 : 플랫폼에 남는 항목의 유형
 * 3. 세금 : 정산 대상에게 부과되는 세금
 *
 *
 */
@Getter
@AllArgsConstructor
@CodeClass
public enum SettlementItemClass implements CommonCode {
    SETTLEMENT("SETTLEMENT", "정산금"),
    COMMISSION("COMMISSION", "수수료"),
    TAX("TAX", "세금");

    private String code;
    private String desc;
}
