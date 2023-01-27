package com.calatheas.skeletonj.common.model;

import com.calatheas.skeletonj.common.code.PartnerType;
import com.calatheas.skeletonj.common.code.RoleType;
import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.session.domain.Session;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class GlobalSession {
    public static final String SESSION = "session";
    public static final String SESSION_ID = "sessionId";
    public static final String PARTNER_ID = "partnerId";
    public static final String PARTNER_NAME = "partnerName";
    public static final String PARTNER_NID = "partnerNid";
    public static final String PARTNER_TYPE = "partnerType";

    public static final String ADMIN_USER_ID = "adminUserId";
    public static final String ADMIN_USER_NID = "adminUserNid";
    public static final String ADMIN_USER_NAME = "adminUserName";
    public static final String ADMIN_USER_ROLE_TYPE = "adminUserRoleType";

    public static void generateGlobalSession(Session session) {
        if (RequestContextHolder.getRequestAttributes() == null) throw BusinessException.of(ExceptionType.UNEXPECTED).setMessage("세션 정보 초기화에 실패하였습니다.");

        RequestContextHolder.getRequestAttributes().setAttribute(SESSION_ID, session.getSessionId(), RequestAttributes.SCOPE_REQUEST);

        RequestContextHolder.getRequestAttributes().setAttribute(PARTNER_NID, session.getPartner().getPartnerNid(), RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.getRequestAttributes().setAttribute(PARTNER_ID, session.getPartner().getPartnerId(), RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.getRequestAttributes().setAttribute(PARTNER_NAME, session.getPartner().getPartnerName(), RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.getRequestAttributes().setAttribute(PARTNER_TYPE, session.getPartner().getPartnerType(), RequestAttributes.SCOPE_REQUEST);

        if (session.getAdminUser() != null) {
            RequestContextHolder.getRequestAttributes().setAttribute(ADMIN_USER_NID, session.getAdminUser().getAdminUserNid(), RequestAttributes.SCOPE_REQUEST);
            RequestContextHolder.getRequestAttributes().setAttribute(ADMIN_USER_ID, session.getAdminUser().getAdminUserId(), RequestAttributes.SCOPE_REQUEST);
            RequestContextHolder.getRequestAttributes().setAttribute(ADMIN_USER_NAME, session.getAdminUser().getAdminUserName(), RequestAttributes.SCOPE_REQUEST);
            RequestContextHolder.getRequestAttributes().setAttribute(ADMIN_USER_ROLE_TYPE, session.getAdminUser().getRoleType().toString(), RequestAttributes.SCOPE_REQUEST);
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

    public static Long getPartnerNid() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (Long) RequestContextHolder.getRequestAttributes().getAttribute(PARTNER_NID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getPartnerId() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(PARTNER_ID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getPartnerName() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(PARTNER_NAME, RequestAttributes.SCOPE_REQUEST);
    }

    public static PartnerType getPartnerType() {
        if (RequestContextHolder.getRequestAttributes() == null) return null;
        return (PartnerType) RequestContextHolder.getRequestAttributes().getAttribute(PARTNER_TYPE, RequestAttributes.SCOPE_REQUEST);
    }

    public static Long getAdminUserNid() {
        return (Long) RequestContextHolder.getRequestAttributes().getAttribute(ADMIN_USER_NID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getAdminUserId() {
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(ADMIN_USER_ID, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getAdminUserName() {
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(ADMIN_USER_NAME, RequestAttributes.SCOPE_REQUEST);
    }

    public static RoleType getAdminUserRoleType() {
        String roleType = (String) RequestContextHolder.getRequestAttributes().getAttribute(ADMIN_USER_ROLE_TYPE, RequestAttributes.SCOPE_REQUEST);
        return RoleType.valueOf(roleType);
    }
}
