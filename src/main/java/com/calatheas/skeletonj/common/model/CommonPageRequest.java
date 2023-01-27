package com.calatheas.skeletonj.common.model;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class CommonPageRequest implements CommonPage {

    @Min(1)
    private Integer pageNo;
    @Min(1)
    @Max(1000)
    private Integer pageSize;

    public CommonPageRequest(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo == null ? 1 : pageNo;
        this.pageSize = pageSize == null ? 10 : pageSize;
    }
}
