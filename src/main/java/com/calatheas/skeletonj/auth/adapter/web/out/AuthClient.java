package com.calatheas.skeletonj.auth.adapter.web.out;

import com.calatheas.skeletonj.auth.adapter.web.model.GetTokenRequest;
import com.calatheas.skeletonj.auth.adapter.web.model.GetTokenResponse;
import com.calatheas.skeletonj.auth.config.AuthClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${internal-service.auth.url}", configuration = AuthClientConfig.class)
public interface AuthClient {

    @PostMapping("/oauth2/token")
    GetTokenResponse getToken(@RequestBody GetTokenRequest getTokenRequest);
}
