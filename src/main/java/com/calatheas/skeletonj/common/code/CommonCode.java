package com.calatheas.skeletonj.common.code;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 코드 클래스 인터페이스
 * enum으로 구현한 코드 클래스의 json 파싱을 위한 인터페이스
 */
public interface CommonCode {
    @JsonValue
    String getCode();

    String getDesc();
}
