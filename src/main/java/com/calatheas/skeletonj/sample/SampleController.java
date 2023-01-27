package com.calatheas.skeletonj.sample;

import com.calatheas.skeletonj.common.annotation.ApiV1Controller;
import com.calatheas.skeletonj.common.code.PartnerType;
import com.calatheas.skeletonj.sample.model.CustomValidationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApiV1Controller
@Validated
public class SampleController {
    @GetMapping("/sample/request-param")
    public SampleModelEnumAndLocalDate sampleRequestParamByAnnotation(@RequestParam PartnerType partnerType, @RequestParam LocalDate startDay, @RequestParam LocalDateTime endDate) {
        return new SampleModelEnumAndLocalDate(partnerType, startDay, endDate);
    }

    @GetMapping("/sample/request-param/model")
    public SampleModelEnumAndLocalDate sampleRequestParamByModel(SampleModelEnumAndLocalDate model) {
        return new SampleModelEnumAndLocalDate(model.getPartnerType(), model.getStartDay(), model.getEndDate());
    }

    @PostMapping("/sample/request-body")
    public SampleModelEnumAndLocalDate sampleRequestBody(@RequestBody SampleModelEnumAndLocalDate model) {
        return new SampleModelEnumAndLocalDate(model.getPartnerType(), model.getStartDay(), model.getEndDate());
    }

    @GetMapping("/sample")
    public SampleModelEnumAndLocalDate sampleSimpleGet() {
        return new SampleModelEnumAndLocalDate(PartnerType.POINT_ISSUE, LocalDate.now(), LocalDateTime.now());
    }

    @GetMapping("/sample/custom-validation")
    public ResponseEntity sampleCustomValidation(@Valid CustomValidationRequest request) {
        return ResponseEntity.ok().build();
    }
}

/**
 * 서버 - 클라이언트 컨버팅 확인하기 위한 모델
 * 1. Enum
 * 2. Date
 */
@Getter
@AllArgsConstructor
class SampleModelEnumAndLocalDate {
    private PartnerType partnerType;
    private LocalDate startDay;
    private LocalDateTime endDate;
}
