package com.calatheas.skeletonj.auth.domain;

import com.calatheas.skeletonj.common.code.TokenType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Token {
    private LocalDateTime expirationDate;
    private TokenType tokenType;
    private String accessToken;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }
}
