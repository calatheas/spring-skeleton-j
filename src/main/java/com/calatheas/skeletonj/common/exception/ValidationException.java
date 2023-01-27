package com.calatheas.skeletonj.common.exception;

import com.calatheas.skeletonj.common.util.CommonBeans;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 입력 데이터 또는 입력 데이터와 연관된 데이터의 validation 수행시 발생하는 예외를 wrapping
 * 여러 필드에 대한 에러 메시지를 한번에 전달
 * case 1. request parameters validation
 * case 2. request body validation
 * case 3. 그 외 비즈니스 로직 수행 전 validation
 */
@Getter
public class ValidationException extends RuntimeException {
    @NonNull
    private ExceptionType exceptionType;
    @NonNull
    private String message;
    private Map<String, String> errorFieldMap;

    private ValidationException(@NonNull String message, Map<String, String> errorFieldMap) {
        super(message);
        this.exceptionType = ExceptionType.INVALID_REQUEST;
        this.message = message;
        this.errorFieldMap = errorFieldMap;
    }

    private ValidationException(Map<String, String> errorFieldMap) {
        super(ExceptionType.INVALID_REQUEST.getMessage());
        this.exceptionType = ExceptionType.INVALID_REQUEST;
        this.message = this.exceptionType.getMessage();
        this.errorFieldMap = errorFieldMap;
    }

    public static ValidationException of(String message, Map<String, String> errorFieldMap) {
        return new ValidationException(message, errorFieldMap);
    }

    public static ValidationException of(String message) {
        return new ValidationException(message, new HashMap<>());
    }

    public static ValidationException of(Map<String, String> errorFieldMap) {
        return new ValidationException(errorFieldMap);
    }

    public void setMessageByCode(String messageCode) {
        this.message = CommonBeans.messageUtil.getMessage(messageCode);
    }
}
