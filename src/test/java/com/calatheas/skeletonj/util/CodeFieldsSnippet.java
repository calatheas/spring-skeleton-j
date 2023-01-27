package com.calatheas.skeletonj.util;

import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.payload.AbstractFieldsSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.util.List;
import java.util.Map;

// code-fields.snippet 템플릿과 이름으로 연결되어 있음
public class CodeFieldsSnippet extends AbstractFieldsSnippet {
    private final String type = "code";

    private CodeFieldsSnippet(String type, List<FieldDescriptor> descriptors, Map<String, Object> attributes, boolean ignoreUndocumentedFields, PayloadSubsectionExtractor<?> subsectionExtractor) {
        super(type, descriptors, attributes, ignoreUndocumentedFields, subsectionExtractor);
    }

    public static CodeFieldsSnippet of(PayloadSubsectionExtractor<?> subsectionExtractor, Map<String, Object> attributes, List<FieldDescriptor> descriptors) {
        return new CodeFieldsSnippet("code", descriptors, attributes, true, subsectionExtractor);
    }

    // 코드값만 표시하므로 사용 안함
    @Override
    protected MediaType getContentType(Operation operation) {
        return null;
    }

    // 코드값만 표시하므로 사용 안함
    @Override
    protected byte[] getContent(Operation operation) {
        return operation.getResponse().getContent();
    }
}
