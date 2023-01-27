package com.calatheas.skeletonj.admin.service;

import com.calatheas.skeletonj.admin.adapter.web.out.AdminClient;
import com.calatheas.skeletonj.admin.domain.Customer;
import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.model.CollectionResponse;
import com.calatheas.skeletonj.common.model.CommonResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminClientPort {
    private final AdminClient adminClient;

    // 인증된 제휴사의 고객 조회
    public CommonResponse<CollectionResponse<Customer>> findCustomers(@NonNull String customerId) {
        try {
            return adminClient.findCustomers(customerId);
        } catch (Exception e) {
            log.error("Admin server request failed", e);
            throw BusinessException.of(ExceptionType.NOT_FOUND);
        }
    }
}
