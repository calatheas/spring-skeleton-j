package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.code.service.model.PointTypeResponse;
import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass(value = PointTypeResponse.class)
public enum PointType implements CommonCode {
    PAY("P", "결제", true, true, true),
    SETTLE("S", "정산", false, false, true),
    MARKETING("M", "마케팅", true, true, false),
    REWARD("R", "리워드", true, false, false);


    private String code;
    private String desc;
    private boolean payable;
    private boolean needSettlementPoint;
    private boolean refundable;
}
