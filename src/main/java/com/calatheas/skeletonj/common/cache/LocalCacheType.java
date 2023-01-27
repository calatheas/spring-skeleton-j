package com.calatheas.skeletonj.common.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocalCacheType {
    PARTNER_SESSION("partnerSession", 60, 10000); // clientId, partnerNid 는 변경되지 않으므로 로컬캐시 적용

    private final String cacheName;
    private final int expiredAfterWriteSec;
    private final int maximumSize;
}
