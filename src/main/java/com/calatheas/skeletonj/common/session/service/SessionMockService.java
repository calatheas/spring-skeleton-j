package com.calatheas.skeletonj.common.session.service;

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
                .sessionId(sessionId)
                .partner(SessionPartner.builder()
                        .partnerId("partnerId")
                        .build())
                .adminUser(SessionUser.builder()
                        .adminUserId("adminUserId")
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
