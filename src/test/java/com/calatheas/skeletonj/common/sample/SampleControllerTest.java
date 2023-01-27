package com.calatheas.skeletonj.common.sample;

import com.calatheas.skeletonj.common.CommonControllerTest;
import com.calatheas.skeletonj.common.code.PartnerType;
import com.calatheas.skeletonj.common.constant.ApiControllerConstants;
import com.calatheas.skeletonj.sample.SampleController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SampleController.class})
class SampleControllerTest extends CommonControllerTest {
    @Test
    public void sampleRequestParamByAnnotation() throws Exception {
        String url = ApiControllerConstants.API_V1 + "/sample/request-param";
        ResultActions resultActions = mockMvc.perform(get(url)
                .param("partnerType", PartnerType.POINT_ISSUE.getCode())
                .param("startDay", "2022-01-01")
                .param("endDate", "2022-01-01 12:00:00")
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void sampleRequestParamByModel() throws Exception {
        String url = ApiControllerConstants.API_V1 + "/sample/request-param/model";
        ResultActions resultActions = mockMvc.perform(get(url)
                .param("partnerType", PartnerType.POINT_ISSUE.getCode())
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
                        Map.of("partnerType", PartnerType.POINT_ISSUE.getCode(),
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
