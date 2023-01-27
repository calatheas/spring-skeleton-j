package com.calatheas.skeletonj.code.adapter.web;

import com.calatheas.skeletonj.code.adapter.web.in.CodeController;
import com.calatheas.skeletonj.code.adapter.web.model.CodeDocumentResponse;
import com.calatheas.skeletonj.code.service.CodeQueryService;
import com.calatheas.skeletonj.common.CommonControllerTest;
import com.calatheas.skeletonj.common.constant.ApiControllerConstants;
import com.calatheas.skeletonj.common.model.CommonResponse;
import com.calatheas.skeletonj.util.CodeFieldsSnippet;
import com.calatheas.skeletonj.util.RestdocsUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({CodeController.class})
class CodeControllerTest extends CommonControllerTest {
    @MockBean
    private CodeQueryService codeQueryService;

    private CodeQueryService _codeQueryService = new CodeQueryService();

    @Test
    public void getCodes() throws Exception {
        // given
        String documentPath = "code/getCodes";
        String url = ApiControllerConstants.API_V1 + "/codes";

        // when
        when(codeQueryService.getCodeQuery()).thenReturn(_codeQueryService.getCodeQuery());
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse()));
    }

    @Test
    public void getCodesForDocuments() throws Exception {
        // given
        String documentPath = "code/getCodesForDocuments";
        String url = ApiControllerConstants.API_V1 + "/codes/documents";

        // when
        when(codeQueryService.getCodeQuery()).thenReturn(_codeQueryService.getCodeQuery());
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url));

        CommonResponse<CodeDocumentResponse> response = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsByteArray(), new TypeReference<CommonResponse<CodeDocumentResponse>>() {
        });

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        convertToCodeFieldsSnippetArray(response.getData().getCodeMap()))
                        );
    }

    private CodeFieldsSnippet[] convertToCodeFieldsSnippetArray(Map<String, Map<String, String>> codeMap) {
        List<CodeFieldsSnippet> codeFieldsSnippets = new ArrayList<>();
        codeMap.forEach(
                (k, v) -> {
                    codeFieldsSnippets.add(CodeFieldsSnippet.of(
                            PayloadDocumentation.beneathPath(String.format("data.codeMap.%s", k)).withSubsectionId(k),
                            attributes(key("title").value(k)),
                            convertToFieldDescriptor(v)
                    ));
                }
        );

        return codeFieldsSnippets.toArray(CodeFieldsSnippet[]::new);
    }

    private List<FieldDescriptor> convertToFieldDescriptor(Map<String, String> codeMap) {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        codeMap.forEach(
                (k, v) -> {
                    fieldDescriptors.add(fieldWithPath(k).description(v));
                }
        );

        return fieldDescriptors;
    }
}
