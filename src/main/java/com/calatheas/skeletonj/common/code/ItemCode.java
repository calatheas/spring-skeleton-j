package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.code.service.model.ItemCodeResponse;
import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass(value = ItemCodeResponse.class)
public enum ItemCode implements CommonCode {
    MAC_BOOK("A01", "발행-자동", ItemCategory.APPLE),
    IPHONE("A02", "발행-수동", ItemCategory.APPLE),
    GALAXY_BOOK("B01", "소멸-현금환불", ItemCategory.SAMSUNG),
    GALAXY_S("B02", "소멸-발행취소", ItemCategory.SAMSUNG);

    private String code;
    private String desc;
    private ItemCategory category;

    public boolean isApple() {
        return category == ItemCategory.APPLE;
    }

    public boolean isSamsung() {
        return category == ItemCategory.SAMSUNG;
    }
}
