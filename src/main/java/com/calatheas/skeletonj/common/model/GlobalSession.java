package com.calatheas.skeletonj.common.model;

import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.session.domain.Session;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Request 컨텍스트 안에서 요청 세션 유지
 */
public class GlobalSession {
    public static final String SESSION = "session";
    public static final String SESSION_ID = "sessionId";
    public static final String PARTNER_ID = "partnerId";

    public static final String ADMIN_USER_ID = "adminUserId";

    public static void generateGlobalSession(Session session) {
        if (RequestContextHolder.getRequestAttributes() == null) throw BusinessException.of(ExceptionType.UNEXPECTED).setMessage("세션 정보 초기화에 실패하였습니다.");

        RequestContextHolder.getRequestAttributes().setAttribute(SESSION_ID, session.getSessionId(), RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.getRequestAttributes().setAttribute(PARTNER_ID, session.getPartner().getPartnerId(), RequestAttributes.SCOPE_REQUEST);

        if (session.getAdminUser() != null) {
            RequestContextHolder.getRequestAttributes().setAttribute(ADMIN_USER_ID, session.getAdminUser().getAdminUserId(), RequestAttributes.SCOPE_REQUEST);
        }
    }

    public static Session getSession() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (Session) RequestContextHolder.getRequestAttributes().getAttribute(SESSION, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getSessionId() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(SESSION_ID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getPartnerId() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(PARTNER_ID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getAdminUserId() {
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(ADMIN_USER_ID, RequestAttributes.SCOPE_REQUEST);
    }
}
