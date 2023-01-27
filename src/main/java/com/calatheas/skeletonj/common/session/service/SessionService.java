package com.calatheas.skeletonj.common.session.service;

import com.calatheas.skeletonj.common.session.domain.Session;

public interface SessionService {
    Session retrieveSession(final String sessionId);

    Session createSession(String clientId);
}
