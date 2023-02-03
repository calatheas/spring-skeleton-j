package com.calatheas.skeletonj.common.model;

import com.calatheas.skeletonj.common.session.domain.Session;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Asnyc flow 에서 기존 세션 유지
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AsyncSession {
    private final static ThreadLocal<Map<String, Object>> ASYNC_SESSION = ThreadLocal.withInitial(() -> new HashMap<String, Object>());

    public static void setSession(Session session) {
        // 세션이 없는 경우, empty 상태 유지
        if (session == null) return;

        AsyncSession.setAttribute(GlobalSession.SESSION_ID, session.getSessionId());
        AsyncSession.setAttribute(GlobalSession.PARTNER_ID, session.getPartner().getPartnerId());

        if (session.getAdminUser() != null) {
            AsyncSession.setAttribute(GlobalSession.ADMIN_USER_ID, session.getAdminUser().getAdminUserId());
        }
    }

    public static void setAttribute(String key, Object value) {
        ASYNC_SESSION.get().putIfAbsent(key, value);
    }

    public static Object getAttribute(String key) {
        return ASYNC_SESSION.get().get(key);
    }

    public static void clear() {
        ASYNC_SESSION.remove();
    }

    public static boolean isEmpty() {
        return ASYNC_SESSION.get().isEmpty();
    }
}
