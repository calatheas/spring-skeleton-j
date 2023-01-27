package com.calatheas.skeletonj.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType implements CommonCode {
    SYSTEM_ADMIN("SYSADMIN", "시스템관리자", true),
    SUPER_ADMIN("SUPERADMIN", "최고관리자", true),
    MANAGER("MANAGER", "매니저", false),
    ACCOUNTING_MANAGER("ACC_MANAGER", "회계담당자", false);

    private String code;
    private String desc;
    private boolean isSuperAdmin;

    public boolean hasEditPermission() {
        return this == SYSTEM_ADMIN || this == SUPER_ADMIN || this == ACCOUNTING_MANAGER;
    }
}
