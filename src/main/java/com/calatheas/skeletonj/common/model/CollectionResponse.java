package com.calatheas.skeletonj.common.model;

import lombok.Getter;

import java.util.List;

@Getter
public class CollectionResponse<T> {

    private List<T> content;
    private boolean hasNext;

    public CollectionResponse(List<T> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }
}
