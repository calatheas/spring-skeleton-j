package com.calatheas.skeletonj.mock;

import com.calatheas.skeletonj.common.model.GlobalSession;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.function.Supplier;

/**
 * Global session mocking class
 * 특정 범위에서만 static method를 mocking 하기 위해 적용 받을 부분을 lambda 로 제공
 * 인터셉터 안의 세션체크 로직 Mocking
 */
public class MockGlobalSession {
    public static <T> T apply(Supplier<T> s) {
        try (MockedStatic<GlobalSession> partnerSessionMockedStatic = Mockito.mockStatic(GlobalSession.class)) {
            partnerSessionMockedStatic.when(GlobalSession::getSessionId).thenReturn("sessionId");
            partnerSessionMockedStatic.when(GlobalSession::getPartnerId).thenReturn("partnerId");

            return s.get();
        }
    }

    public static MockedStatic<GlobalSession> get() {
        MockedStatic<GlobalSession> partnerSessionMockedStatic = Mockito.mockStatic(GlobalSession.class);
        partnerSessionMockedStatic.when(GlobalSession::getSessionId).thenReturn("sessionId");
        partnerSessionMockedStatic.when(GlobalSession::getPartnerId).thenReturn("partnerId");
        return partnerSessionMockedStatic;
    }
}
