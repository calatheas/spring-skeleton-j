package com.calatheas.skeletonj.common.exception;

import com.calatheas.skeletonj.common.util.CommonBeans;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 공통 예외 유형
 * 공통 응답의 code, status, default message 를 구조화
 */
@RequiredArgsConstructor
public enum ExceptionType {
    // universal - 전체 공통 에러
    U_EXPIRED_SESSION("007", HttpStatus.UNAUTHORIZED.value(), "exception.common.unauthorized.expiredSession"),

    // standard
    BAD_REQUEST("301", HttpStatus.BAD_REQUEST.value(), "exception.standard.badRequest"),
    UNAUTHORIZED("302", HttpStatus.UNAUTHORIZED.value(), "exception.standard.unauthorized"),
    FORBIDDEN("303", HttpStatus.FORBIDDEN.value(), "exception.standard.forbidden"),
    NOT_FOUND("304", HttpStatus.NOT_FOUND.value(), "exception.standard.notFound"),
    UNEXPECTED("305", HttpStatus.INTERNAL_SERVER_ERROR.value(), "exception.standard.unexpected"),

    // common
    DUPLICATE_PK("311", HttpStatus.BAD_REQUEST.value(), "exception.common.duplicate.pk"),
    NOT_IMPLEMENTED("312", HttpStatus.INTERNAL_SERVER_ERROR.value(), "exception.standard.unexpected"),
    NO_PERMISSION_EDIT("313", HttpStatus.UNAUTHORIZED.value(), "exception.common.noPermission.edit"),

    // business
    INVALID_REQUEST("321", HttpStatus.BAD_REQUEST.value(), "exception.common.validation.request"),
    DISABLED_REDISSON_CUSTOMER_LOCK("321", HttpStatus.FORBIDDEN.value(), "exception.business.disabled.customer.lock"),
    UNDEFINED_ERROR_398("322", HttpStatus.FORBIDDEN.value(), "exception.common.undefined"),
    UNDEFINED_ERROR_399("323", HttpStatus.FORBIDDEN.value(), "exception.common.undefined");
    ;
    @NonNull
    private final String code;
    private final int status;
    private final String messageCode;

    public String getCode() {
        return status + code;
    }

    public int getStatus() {
        return status;
    }

    /**
     * Notice.Use after application context loading
     */
    public String getMessage() {
        try {
            return CommonBeans.messageUtil.getMessage(messageCode);
        } catch (Exception e) {
            return messageCode;
        }
    }

    public String getMessage(Object... args) {
        try {
            return CommonBeans.messageUtil.getMessage(messageCode, args);
        } catch (Exception e) {
            return messageCode;
        }
    }
}
