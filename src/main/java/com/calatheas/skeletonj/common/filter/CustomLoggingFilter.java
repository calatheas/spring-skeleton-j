package com.calatheas.skeletonj.common.filter;

import com.calatheas.skeletonj.common.code.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Slf4j
public class CustomLoggingFilter extends OncePerRequestFilter {
    private static final String MDC_CORRELATION_ID = "correlationId";
    private static final List<String> EXCLUDE_ALL_URL = List.of("/healthy");
    private static final List<String> EXCLUDE_BODY_URL_REGEX_LIST = List.of(".*login", ".*password", ".*partners/session");
    private Profile profile;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomLoggingFilter(String profile) {
        this.profile = Profile.fromCode(profile);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Set request correlation id
        MDC.put(MDC_CORRELATION_ID, UUID.randomUUID().toString());

        // Cache request data
        ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);
        filterChain.doFilter(cachingRequest, response);

        try {
            if (HttpStatus.valueOf(response.getStatus()).isError()) {
                log.error(createMessage(cachingRequest, response));
            } else {
                if (!EXCLUDE_ALL_URL.contains(request.getRequestURI())) {
                    log.info(createMessage(cachingRequest, response));
                }
            }
        } catch (Exception e) {
            log.error("Request logging failed");
        }
    }

    private boolean isLoggingUrl(String url) {
        for (String regex : EXCLUDE_BODY_URL_REGEX_LIST) {
            if (url.matches(regex)) {
                return false;
            }
        }

        return true;
    }

    /**
     * request url, Query parameters, headers, response status 를 이용하여 메시지를 만든다.
     * todo 개인정보의 위험이 있는 정보는 마스킹 처리
     * todo 개인정보의 위험이 있는 정보는 제외할 수 있는 구조 마련
     *
     * @param request
     * @param response
     * @return
     */
    private String createMessage(ContentCachingRequestWrapper request, HttpServletResponse response) throws IOException {
        StringBuilder msg = new StringBuilder();

        msg.append(request.getMethod()).append(' ');
        msg.append(request.getRequestURI());

        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append('?').append(queryString);
        }

        msg.append(" ");
        msg.append(response.getStatus()).append('\n');

        // todo 헤더 정보 중에서 session 만 추출하고 나머지는 API 게이트웨이 엑세스로그로 확인
        HttpHeaders headers = new ServletServerHttpRequest(request).getHeaders();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String header = names.nextElement();
            headers.set(header, request.getHeader(header));
        }
        msg.append("[H]\t").append(headers);

        if (isLoggingUrl(request.getRequestURI())) {
            msg.append('\n');
            if (request.getContentType() != null && request.getContentType().contains("application/json")) {
                if (request.getContentAsByteArray() != null && request.getContentAsByteArray().length != 0) {
                    msg.append("[B]\t").append(objectMapper.readTree(request.getContentAsByteArray()));
                }
            }
        }

        return msg.toString();
    }

    private String maskValue(String value) {
        if (value == null) {
            return null;
        } else if (profile.isDevEnvironment()) {
            return value;
        } else {
            // todo 헤더 종류 별로 masking 해제
            int numberUnmaskedChar = 3;
            String pattern = String.format("(?<=.{%s}).", numberUnmaskedChar);
            return value.replaceAll(pattern, "*");
        }
    }
}
