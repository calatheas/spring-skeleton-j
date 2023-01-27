package com.calatheas.skeletonj.common.session.domain;

import com.calatheas.skeletonj.common.code.PartnerType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionPartner {
    private Long partnerNid;
    private String partnerId;
    private String partnerName;
    private PartnerType partnerType;
}
