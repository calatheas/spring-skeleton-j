package com.calatheas.skeletonj.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrantType implements CommonCode {
    CREDENTIALS("client_credentials", "Client ID/Secret"),
    REFRESH_TOKEN("refresh_token", "Refresh token"),
    PASSWORD("password", "Password");

    private String code;
    private String desc;
}
