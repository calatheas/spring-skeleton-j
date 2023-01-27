package com.calatheas.skeletonj.code.adapter.web.in;

import com.calatheas.skeletonj.code.adapter.web.model.ErrorQuery;
import com.calatheas.skeletonj.common.annotation.ApiV1Controller;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApiV1Controller
@RequiredArgsConstructor
public class ErrorCodeController {
    @GetMapping("/errors")
    public CommonResponse<List<ErrorQuery>> getErrors() {
        return CommonResponse.ok(Arrays.stream(ExceptionType.values())
                .map(it -> ErrorQuery.of(it))
                .collect(Collectors.toList()));
    }
}
