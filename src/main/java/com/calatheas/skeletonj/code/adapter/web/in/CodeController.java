package com.calatheas.skeletonj.code.adapter.web.in;

import com.calatheas.skeletonj.code.adapter.web.model.CodeDocumentResponse;
import com.calatheas.skeletonj.code.service.CodeQueryService;
import com.calatheas.skeletonj.code.service.model.CodeQuery;
import com.calatheas.skeletonj.common.annotation.ApiV1Controller;
import com.calatheas.skeletonj.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@ApiV1Controller
@RequiredArgsConstructor
public class CodeController {
    private final CodeQueryService codeQueryService;

    @GetMapping("/codes")
    public CommonResponse<CodeQuery> getCodes() {
        return CommonResponse.ok(codeQueryService.getCodeQuery());
    }

    // Restdocs 작성을 위해 별도 API 신설
    @GetMapping("/codes/documents")
    public CommonResponse<CodeDocumentResponse> getCodesForDocuments() throws IOException {
        return CommonResponse.ok(CodeDocumentResponse.make(codeQueryService.getCodeQuery()));
    }
}
