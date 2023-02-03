package com.calatheas.skeletonj.auth.service;

import com.calatheas.skeletonj.auth.adapter.web.model.GetTokenRequest;
import com.calatheas.skeletonj.auth.adapter.web.out.AuthClient;
import com.calatheas.skeletonj.auth.domain.Token;
import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthPort {
    private final AuthClient authClient;

    // 인증된 제휴사의 고객 조회
    public Token getToken(@NonNull String clientId, @NonNull String clientSecret) {
        try {
            return authClient.getToken(GetTokenRequest.ofCredentials(clientId, clientSecret)).convertTo();
        } catch (Exception e) {
            log.error("Auth server request failed", e);
            throw BusinessException.of(ExceptionType.NOT_FOUND);
        }
    }
}
