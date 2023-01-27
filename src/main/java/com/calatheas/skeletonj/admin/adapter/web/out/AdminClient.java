package com.calatheas.skeletonj.admin.adapter.web.out;

import com.calatheas.skeletonj.admin.config.AdminClientConfig;
import com.calatheas.skeletonj.admin.domain.Customer;
import com.calatheas.skeletonj.common.model.CollectionResponse;
import com.calatheas.skeletonj.common.model.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "adminClient", url = "${internal-service.admin.api-url}", configuration = AdminClientConfig.class)
public interface AdminClient {

    @GetMapping("/customers")
    CommonResponse<CollectionResponse<Customer>> findCustomers(@RequestParam String customerId);
}
