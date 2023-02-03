package com.calatheas.skeletonj.common.sample;

import com.calatheas.skeletonj.common.CommonControllerTest;
import com.calatheas.skeletonj.common.code.ItemCode;
import com.calatheas.skeletonj.common.constant.ApiControllerConstants;
import com.calatheas.skeletonj.sample.SampleController;
import com.calatheas.skeletonj.util.RestdocsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static com.calatheas.skeletonj.util.RestdocsUtils.createCodeLink;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SampleController.class})
class SampleControllerTest extends CommonControllerTest {
    @Test
    public void sampleRequestParamByAnnotation() throws Exception {
        String documentPath = "sample/sampleRequestParamByAnnotation";
        String url = ApiControllerConstants.API_V1 + "/sample/request-param";
        ResultActions resultActions = mockMvc.perform(get(url)
                .param("itemCode", ItemCode.MAC_BOOK.getCode())
                .param("startDay", "2022-01-01")
                .param("endDate", "2022-01-01 12:00:00")
        );

        //then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.requestParameters(
                                RestdocsUtils.parameterWithNamAndType("itemCode", "String").description(createCodeLink(ItemCode.class, "아이템코드")),
                                RestdocsUtils.parameterWithNamAndType("startDay", "String").description("시작일자"),
                                RestdocsUtils.parameterWithNamAndType("endDate", "String").optional().description("종료일자")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("itemCode").type(JsonFieldType.STRING).description(createCodeLink(ItemCode.class, "아이템코드")),
                                PayloadDocumentation.fieldWithPath("startDay").type(JsonFieldType.STRING).description("시작일자"),
                                PayloadDocumentation.fieldWithPath("endDate").type(JsonFieldType.STRING).description("종료일자")
                        )
                ));
    }

    @Test
    public void sampleRequestParamByModel() throws Exception {
        String url = ApiControllerConstants.API_V1 + "/sample/request-param/model";
        ResultActions resultActions = mockMvc.perform(get(url)
                .param("itemCode", ItemCode.MAC_BOOK.getCode())
                .param("startDay", "2022-01-01")
                .param("endDate", "2022-01-01 12:00:00")
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void sampleRequestBody() throws Exception {
        String url = ApiControllerConstants.API_V1 + "/sample/request-body";

        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        Map.of("itemCode", ItemCode.MAC_BOOK.getCode(),
                                "startDay", "2022-01-01",
                                "endDate", "2022-01-01 12:00:00")
                ))
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void sampleSimpleGet() throws Exception {
        String url = ApiControllerConstants.API_V1 + "/sample";

        ResultActions resultActions = mockMvc.perform(get(url));

        resultActions.andExpect(status().isOk());
    }
}
