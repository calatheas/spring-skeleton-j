package com.calatheas.skeletonj.common.exception;

import com.calatheas.skeletonj.common.util.CommonBeans;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * 비즈니스 로직에서 발생하는 예외를 wrapping
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {
    @NonNull
    private final ExceptionType exceptionType;
    @NonNull
    private String message;

    public static BusinessException of(ExceptionType exceptionType, String message) {
        return new BusinessException(exceptionType, message);
    }

    public static BusinessException of(ExceptionType exceptionType) {
        return new BusinessException(exceptionType, exceptionType.getMessage());
    }

    public static BusinessException of(ExceptionType exceptionType, Object... args) {
        return new BusinessException(exceptionType, exceptionType.getMessage(args));
    }

    public BusinessException setMessageByCode(String messageCode) {
        this.message = CommonBeans.messageUtil.getMessage(messageCode);
        return this;
    }

    public BusinessException setMessageByCode(String messageCode, Object... args) {
        this.message = CommonBeans.messageUtil.getMessage(messageCode, args);
        return this;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }

    public BusinessException appendMessage(String message) {
        this.message = this.message+message;
        return this;
    }
}
