package com.calatheas.skeletonj.code.service.model;

import com.calatheas.skeletonj.common.code.CommonCode;
import com.calatheas.skeletonj.common.code.ItemCategory;
import com.calatheas.skeletonj.common.code.ItemCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCodeResponse extends BaseCode {
    private ItemCategory category;

    private ItemCodeResponse(CommonCode commonCode) {
        super(commonCode);

        if (commonCode instanceof ItemCode) {
            category = ((ItemCode) commonCode).getCategory();
        }
    }

    public static ItemCodeResponse of(CommonCode commonCode) {
        return new ItemCodeResponse(commonCode);
    }
}
