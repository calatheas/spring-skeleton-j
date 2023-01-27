package com.calatheas.skeletonj.sample.model;

import com.calatheas.skeletonj.common.annotation.ValidCustomerId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ValidCustomerId
public class CustomValidationRequest {
    private Long customerNid;
    private String customerId;
}
