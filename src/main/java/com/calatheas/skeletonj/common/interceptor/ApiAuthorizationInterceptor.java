package com.calatheas.skeletonj.common.interceptor;

import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.model.GlobalSession;
import com.calatheas.skeletonj.common.session.domain.Session;
import com.calatheas.skeletonj.common.session.service.SessionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiAuthorizationInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    public static final String SESSION_ID_HEADER_KEY = "x-session-id";
    public static final String PARTNER_NID_HEADER_KEY = "x-partner-nid";

    public static List<String> EDIT_HTTP_METHODS = List.of(HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // preflight 에서는 request method, header 는 access-control-request-method, access-control-request-headers 으로 들어감
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String sessionId = request.getHeader(SESSION_ID_HEADER_KEY);
        String partnerNid = request.getHeader(PARTNER_NID_HEADER_KEY);

        if (!StringUtils.hasText(sessionId)) {
            throw BusinessException.of(ExceptionType.INVALID_REQUEST, SESSION_ID_HEADER_KEY + " is required inside the header.");
        } else if (!StringUtils.hasText(partnerNid)) {
            throw BusinessException.of(ExceptionType.INVALID_REQUEST, PARTNER_NID_HEADER_KEY + " is required inside the header.");
        }

        Session session;
        try {
            session = sessionService.retrieveSession(sessionId);
            GlobalSession.generateGlobalSession(session);
        } catch (NumberFormatException e) {
            throw BusinessException.of(ExceptionType.UNAUTHORIZED, "Invalid partner nid");
        } catch (Exception e) {
            log.error("{}", e);
            throw BusinessException.of(ExceptionType.U_EXPIRED_SESSION);
        }

        checkEditPermission(request, session);

        return true;
    }

    private void checkEditPermission(HttpServletRequest request, @NonNull Session session) {
        if (session.getAdminUser() != null) {
            if (session.getAdminUser().getRoleType() == null){
                throw BusinessException.of(ExceptionType.UNEXPECTED).setMessage("Session.adminUser.roleType 은 null 일 수 없습니다.");
            }
            else if(!session.getAdminUser().getRoleType().hasEditPermission()) {
                if(EDIT_HTTP_METHODS.contains(request.getMethod())) {
                    throw BusinessException.of(ExceptionType.NO_PERMISSION_EDIT);
                }
            }
        }
    }
}
