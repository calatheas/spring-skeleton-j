package com.calatheas.skeletonj.common.session.domain;

import com.calatheas.skeletonj.common.code.RoleType;
import com.calatheas.skeletonj.common.code.Yn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionUser {
    private Long adminUserNid;
    private String adminUserId;
    private String adminUserName;
    private RoleType roleType;
    private String email;
    private String phoneNo;
    private Yn temporaryPasswordYn;
}
