package com.calatheas.skeletonj.util;

import org.springframework.test.web.servlet.ResultActions;

@FunctionalInterface
public interface GeneratingRestdocsFunction {
    void generate(ResultActions results, String documentPath);
}
