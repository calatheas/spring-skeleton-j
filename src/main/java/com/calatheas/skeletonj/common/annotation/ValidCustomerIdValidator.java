package com.calatheas.skeletonj.common.annotation;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCustomerIdValidator implements ConstraintValidator<ValidCustomerId, Object> {

    @Override
    public void initialize(ValidCustomerId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String customerId = (String) new BeanWrapperImpl(value).getPropertyValue("customerId");
        Long customerNid = (Long) new BeanWrapperImpl(value).getPropertyValue("customerNid");

        if ((StringUtils.isEmpty(customerId) && ObjectUtils.isNotEmpty(customerNid)) ||
                (StringUtils.isNotEmpty(customerId) && ObjectUtils.isEmpty(customerNid))) {
            return true;
        }

        String nullValue = StringUtils.isEmpty(customerId) ? "customerId" : "customerNid";
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("customerId or customerNid not null")
                .addPropertyNode(nullValue).addConstraintViolation();

        return false;
    }
}
