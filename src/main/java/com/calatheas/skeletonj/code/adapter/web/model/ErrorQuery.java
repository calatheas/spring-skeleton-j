package com.calatheas.skeletonj.code.adapter.web.model;

import com.calatheas.skeletonj.common.exception.ExceptionType;
import lombok.Getter;

@Getter
public class ErrorQuery {
    private String name;
    private String code;
    private Integer status;
    private String message;

    public static ErrorQuery of(ExceptionType exceptionType) {
        ErrorQuery errorQuery = new ErrorQuery();
        errorQuery.code = exceptionType.getCode();
        errorQuery.name = exceptionType.name();
        errorQuery.status = exceptionType.getStatus();
        errorQuery.message = exceptionType.getMessage();
        return errorQuery;
    }
}
