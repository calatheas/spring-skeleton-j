package com.calatheas.skeletonj.common;

import com.calatheas.skeletonj.common.model.GlobalSession;
import com.calatheas.skeletonj.mock.MockGlobalSession;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
public abstract class CommonControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private MockedStatic<GlobalSession> partnerSessionMockedStatic;

    @BeforeEach
    protected void setup() {
        objectMapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);

        // interceptor 메소드 mocking 하지 않으면 controller 로 들어가지 않음
        // Mockito.when(interceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true)

        // 매 테스트 마다 세션 생성 (static method mocking)
        partnerSessionMockedStatic = MockGlobalSession.get();
    }

    @AfterEach
    protected void done() {
        partnerSessionMockedStatic.close();
    }
}
