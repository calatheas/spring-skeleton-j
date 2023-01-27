package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum PointProgressStatusCategory implements CommonCode {

    REQUEST("R", "요청", false),
    APPROVAL("A", "승인", false),
    ONCHAIN("O", "블록체인 기록", false),
    SUCCESS("S", "성공", true),
    BANKING("B", "이체", true);

    private String code;
    private String desc;
    private boolean isLastStage;

}
