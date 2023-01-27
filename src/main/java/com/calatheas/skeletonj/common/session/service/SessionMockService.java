package com.calatheas.skeletonj.common.session.service;

import com.calatheas.skeletonj.common.code.PartnerType;
import com.calatheas.skeletonj.common.session.domain.Session;
import com.calatheas.skeletonj.common.session.domain.SessionPartner;
import com.calatheas.skeletonj.common.session.domain.SessionUser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessionMockService implements SessionService {
    private Session getMockSession(String sessionId) {
        return Session.builder()
                .sessionId("sessionId")
                .partner(SessionPartner.builder()
                        .partnerId("partnerId")
                        .partnerNid(1L)
                        .partnerName("partnerName")
                        .partnerType(PartnerType.POINT_ISSUE)
                        .build())
                .adminUser(SessionUser.builder()
                        .adminUserId("adminUserId")
                        .adminUserNid(1000L)
                        .adminUserName("adminUserName")
                        .build())
                .build();
    }

    @Override
    public Session retrieveSession(@NonNull String sessionId) {
        return getMockSession(sessionId);
    }

    @Override
    public Session createSession(String clientId) {
        return getMockSession(clientId);
    }
}
