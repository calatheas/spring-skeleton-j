package com.calatheas.skeletonj.auth.adapter.web.model;

import com.calatheas.skeletonj.auth.domain.Token;
import com.calatheas.skeletonj.common.code.TokenType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetTokenResponse {
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("token_type")
    private TokenType tokenType;
    @JsonProperty("access_token")
    private String accessToken;

    public Token convertTo() {
        return Token.builder()
                .accessToken(accessToken)
                .tokenType(tokenType)
                .expirationDate(LocalDateTime.now().plusSeconds(expiresIn))
                .build();
    }
}
