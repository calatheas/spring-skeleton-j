package com.calatheas.skeletonj.common.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CommonPage {

    Integer getPageNo();

    Integer getPageSize();

    default Pageable getPageable() {
        return PageRequest.of(getPageNo() - 1, getPageSize());
    }
}
