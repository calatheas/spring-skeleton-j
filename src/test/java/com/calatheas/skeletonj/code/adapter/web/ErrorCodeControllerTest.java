package com.calatheas.skeletonj.code.adapter.web;

import com.calatheas.skeletonj.code.adapter.web.in.ErrorCodeController;
import com.calatheas.skeletonj.common.CommonControllerTest;
import com.calatheas.skeletonj.common.constant.ApiControllerConstants;
import com.calatheas.skeletonj.util.RestdocsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ErrorCodeController.class})
class ErrorCodeControllerTest extends CommonControllerTest {
    @Test
    public void getErrors() throws Exception {
        // given
        String documentPath = "exception/getErrors";
        String url = ApiControllerConstants.API_V1 + "/errors";

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse()));
    }
}
