package com.calatheas.skeletonj.common.component;

import com.calatheas.skeletonj.common.util.MessageUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled // 스프링 부트 테스트 중지 (CI 환경이 없음)
@SpringBootTest
class MessageUtilTest {
    @Autowired
    MessageUtil messageUtil;

    @Test
    public void getMessage() {
        String messageCode = "exception.standard.unexpected";
        String message = "Unexpected error";
        Assertions.assertEquals(messageUtil.getMessage(messageCode), message);
    }
}
