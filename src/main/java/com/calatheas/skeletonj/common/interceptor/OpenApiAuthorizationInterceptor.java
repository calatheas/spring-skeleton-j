package com.calatheas.skeletonj.common.interceptor;

import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.model.GlobalSession;
import com.calatheas.skeletonj.common.session.domain.Session;
import com.calatheas.skeletonj.common.session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class OpenApiAuthorizationInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    public static final String GW_CLIENT_ID_IN_OPEN_API_HEADER = "x-consumer-custom-id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String gatewayClientId = request.getHeader(GW_CLIENT_ID_IN_OPEN_API_HEADER);

        if (!StringUtils.hasText(gatewayClientId)) {
            throw BusinessException.of(ExceptionType.INVALID_REQUEST, GW_CLIENT_ID_IN_OPEN_API_HEADER + " is required inside the header.");
        }

        try {
            // OpenAPI 에서는 clientId 로 session 생성
            Session session = sessionService.createSession(gatewayClientId);
            GlobalSession.generateGlobalSession(session);
        } catch (Exception e) {
            throw BusinessException.of(ExceptionType.UNAUTHORIZED).setMessageByCode("exception.business.notFound.clientId.partner", gatewayClientId);
        }

        return true;
    }
}
