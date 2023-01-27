package com.calatheas.skeletonj.common.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Getter
public class ValidationExceptionResponse extends ExceptionResponse {
    private Map<String, String> errorFieldMap;

    private ValidationExceptionResponse(@NonNull String code, @NonNull String message, Map<String, String> errorFieldMap) {
        super(code, message);
        this.errorFieldMap = errorFieldMap;
    }

    public static ValidationExceptionResponse of(@NonNull final String code, @NonNull final String message, final Map<String, String> errorFieldMap) {
        return new ValidationExceptionResponse(code, message, errorFieldMap);
    }
}