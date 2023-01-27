package com.calatheas.skeletonj.common.session.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class Session {
    @NonNull
    private String sessionId;
    @NonNull
    private SessionPartner partner;
    private SessionUser adminUser;
}
