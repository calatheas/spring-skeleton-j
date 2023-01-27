package com.calatheas.skeletonj.common.exception;

import com.calatheas.skeletonj.common.model.ExceptionResponse;
import com.calatheas.skeletonj.common.model.ValidationExceptionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private ObjectMapper objectMapper;

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<Object> handleBusinessException(final BusinessException businessException) {
        log.error("Business exception occurred", businessException);

        return ResponseEntity.status(businessException.getExceptionType().getStatus()).body(ExceptionResponse.of(businessException));
    }

    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<Object> handleValidationException(final ValidationException validationException) throws JsonProcessingException {
        log.error("Validation exception occurred - {}", objectMapper.writeValueAsString(validationException.getErrorFieldMap()));

        return ResponseEntity.badRequest().body(
                ValidationExceptionResponse.of(
                        ExceptionType.INVALID_REQUEST.getCode(),
                        validationException.getMessage(),
                        validationException.getErrorFieldMap()
                ));
    }

    /**
     * MethodArgumentNotValidException 발생시 응답 처리
     * 1. 처리 방법
     * - MethodArgumentNotValidException 에 여러 필드의 위반 사항이 들어와서 사용자에게 위반사항을 한번에 전달
     * - MethodArgumentNotValidException.BindingResult.FieldErrors
     * 2. 발생 요건
     * - javax.validation.Valid
     * - org.springframework.web.bind.annotation.RequestBody
     * - javax.validation.constraints.*
     * 3. 발생되지 않는 경우
     * - request body 전체가 안들어 오는 경우 HttpMessageNotReadableException 발생 (빈 json string 이라도 들어오면 MethodArgumentNotValidException 발생)
     * <p>
     * 컨트롤러에 @RequestBody 안붙이고 object 를 매핑할때
     * json 신택스 에러(ex, 컴마가 한개 더 들어감)
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    private ResponseEntity<Object> handleBindException(final BindException bindException) throws JsonProcessingException {
        List<FieldError> fieldErrorList = bindException.getBindingResult().getFieldErrors();
        Map<String, String> errorFieldMap = fieldErrorList.stream().collect(
                Collectors.toMap(FieldError::getField,
                        it -> it.getDefaultMessage()
                ));

        log.error("Validation exception occurred - {}", objectMapper.writeValueAsString(errorFieldMap));

        return ResponseEntity.badRequest().body(
                ValidationExceptionResponse.of(
                        ExceptionType.INVALID_REQUEST.getCode(),
                        ExceptionType.INVALID_REQUEST.getMessage(),
                        errorFieldMap)
        );
    }

    /**
     * ConstraintViolationException 발생시 응답 처리
     * 1. 처리 방법
     * - ConstraintViolationException.getMessage() 에 여러 필드의 위반 사항이 들어와서 사용자에게 위반사항을 한번에 전달
     * 2. 발생 요건
     * - org.springframework.validation.annotation.Validated
     * - org.springframework.web.bind.annotation.PathVariable
     * - org.springframework.web.bind.annotation.RequestParam
     * - javax.validation.constraints.*
     * - PathVariable, RequestParam 에 직접 constraints 를 사용하는 경우, 컨트롤러에 @Validated 필요함
     * 3. 발생되지 않는 경우
     * - PathVariable 이 필수인데 빈 문자열이거나 공백으로 들어오는 경우, 없는 것으로 판단하고 url 매핑이 안됨
     * - RequestParam 이 필수인데 없는 경우(null 인 경우), MissingServletRequestParameterException 발생
     * <p>
     * MissingServletRequestParameterException 발생시 응답 처리
     * - RequestParam 필수항목이 없는 경우, 1개씩 발생함
     * - 필수를 해제하고 ConstraintViolationException 으로 처리하는게 바람직함
     */
    @ExceptionHandler({ConstraintViolationException.class, MissingServletRequestParameterException.class})
    private ResponseEntity<Object> handleConstraintViolationException(final Exception e) throws JsonProcessingException {
        if (e instanceof ConstraintViolationException) {
            Map<String, String> errorFieldMap = resolveErrorFieldMap((ConstraintViolationException) e);
            log.error("Validation exception occurred - {}", objectMapper.writeValueAsString(errorFieldMap));

            return ResponseEntity.badRequest().body(
                    ValidationExceptionResponse.of(
                            ExceptionType.INVALID_REQUEST.getCode(),
                            ExceptionType.INVALID_REQUEST.getMessage(),
                            errorFieldMap)
            );
        } else {
            log.error("Validation exception occurred", e);

            return ResponseEntity.badRequest().body(
                    new ExceptionResponse(
                            ExceptionType.BAD_REQUEST.getCode(),
                            e.getMessage()
                    )
            );
        }
    }

    private Map<String, String> resolveErrorFieldMap(ConstraintViolationException e) {
        return e.getConstraintViolations().stream().collect(Collectors.toMap(
                it -> it.getPropertyPath().toString(),
                it -> it.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleUnexpectedException(final Exception e) {
        log.error("Unexpected error", e);

        return ResponseEntity.internalServerError().body(
                new ExceptionResponse(
                        ExceptionType.UNEXPECTED.getCode(),
                        e.getMessage()
                )
        );
    }
}
