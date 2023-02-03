package com.calatheas.skeletonj.auth.service;

import com.calatheas.skeletonj.auth.adapter.web.model.GetTokenResponse;
import com.calatheas.skeletonj.auth.adapter.web.out.AuthClient;
import com.calatheas.skeletonj.auth.domain.Token;
import com.calatheas.skeletonj.common.code.TokenType;
import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import feign.RetryableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthPortTest {
    @InjectMocks
    private AuthPort authPort;

    @Mock
    private AuthClient authClient;

    @Test
    public void getToken() {
        // given
        Integer expiresIn = 0;
        GetTokenResponse response = GetTokenResponse.builder()
                .expiresIn(expiresIn)
                .tokenType(TokenType.BEARER)
                .accessToken("accessToken")
                .build();

        // when, 결과 없음
        when(authClient.getToken(any()))
                .thenThrow(RetryableException.class)
                .thenReturn(response);

        // then
        BusinessException e = Assertions.assertThrows(
                BusinessException.class,
                () -> authPort.getToken("clientId", "clientSecret"));

        Assertions.assertEquals(e.getExceptionType(), ExceptionType.NOT_FOUND);

        Token token = authPort.getToken("clientId", "clientSecret");

        Assertions.assertTrue(token.isExpired());
    }
}
