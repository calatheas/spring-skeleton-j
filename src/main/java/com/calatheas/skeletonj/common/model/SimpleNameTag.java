package com.calatheas.skeletonj.common.model;

import lombok.Getter;

@Getter
public class SimpleNameTag {
    private Long nid;
    private String name;

    public static SimpleNameTag of(Long nid) {
        SimpleNameTag simpleNameTag = new SimpleNameTag();
        simpleNameTag.nid = nid;
        return simpleNameTag;
    }

    public static SimpleNameTag of(Long nid, String name) {
        SimpleNameTag simpleNameTag = new SimpleNameTag();
        simpleNameTag.nid = nid;
        simpleNameTag.name = name;
        return simpleNameTag;
    }

    public void setName(String name) {
        this.name = name;
    }
}
