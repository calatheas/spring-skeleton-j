package com.calatheas.skeletonj.code.service;

import org.junit.jupiter.api.Test;

class CodeQueryServiceTest {
    CodeQueryService codeQueryService = new CodeQueryService();

    @Test
    public void getCodeQuery() {
        codeQueryService.getCodeQuery().getCodeMap().forEach(
                (k, v) -> System.out.println(v)
        );
    }
}