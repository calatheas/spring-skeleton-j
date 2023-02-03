package com.calatheas.skeletonj.auth.adapter.web.model;

import com.calatheas.skeletonj.common.code.GrantType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class GetTokenRequest {
    @JsonProperty("grant_type")
    private GrantType grantType;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;

    public static GetTokenRequest ofCredentials(@NonNull String clientId, @NonNull String clientSecret) {
        GetTokenRequest request = new GetTokenRequest();
        request.grantType = GrantType.CREDENTIALS;
        request.clientId = clientId;
        request.clientSecret = clientSecret;
        return request;
    }
}
