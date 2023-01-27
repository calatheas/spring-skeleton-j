package com.calatheas.skeletonj.mock;

import com.calatheas.skeletonj.common.code.PartnerType;
import com.calatheas.skeletonj.common.model.GlobalSession;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.function.Supplier;

/**
 * Global session mocking class
 * 특정 범위에서만 static method를 mocking 하기 위해 적용 받을 부분을 lambda 로 제공
 */
public class MockGlobalSession {
    public static <T> T apply(Supplier<T> s) {
        try (MockedStatic<GlobalSession> partnerSessionMockedStatic = Mockito.mockStatic(GlobalSession.class)) {
            partnerSessionMockedStatic.when(GlobalSession::getSessionId).thenReturn("sessionId");
            partnerSessionMockedStatic.when(GlobalSession::getPartnerNid).thenReturn(1L);
            partnerSessionMockedStatic.when(GlobalSession::getPartnerId).thenReturn("partnerId");
            partnerSessionMockedStatic.when(GlobalSession::getPartnerName).thenReturn("partnerName");

            return s.get();
        }
    }

    public static MockedStatic<GlobalSession> get() {
        MockedStatic<GlobalSession> partnerSessionMockedStatic = Mockito.mockStatic(GlobalSession.class);
        partnerSessionMockedStatic.when(GlobalSession::getSessionId).thenReturn("sessionId");
        partnerSessionMockedStatic.when(GlobalSession::getPartnerNid).thenReturn(1L);
        partnerSessionMockedStatic.when(GlobalSession::getPartnerId).thenReturn("partnerId");
        partnerSessionMockedStatic.when(GlobalSession::getPartnerName).thenReturn("partnerName");
        partnerSessionMockedStatic.when(GlobalSession::getPartnerType).thenReturn(PartnerType.POINT_ISSUE);
        return partnerSessionMockedStatic;
    }
}
