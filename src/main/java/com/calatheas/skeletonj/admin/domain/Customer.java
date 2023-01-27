package com.calatheas.skeletonj.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class Customer {

    @NonNull
    private Long customerNid;
    @NonNull
    private Long partnerNid;
    @NonNull
    private String customerId;
    private String iamAccountId;
}
