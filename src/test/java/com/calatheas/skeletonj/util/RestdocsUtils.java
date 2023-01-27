package com.calatheas.skeletonj.util;

import com.calatheas.skeletonj.common.code.CommonCode;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.payload.SubsectionDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.calatheas.skeletonj.common.interceptor.ApiAuthorizationInterceptor.PARTNER_NID_HEADER_KEY;
import static com.calatheas.skeletonj.common.interceptor.ApiAuthorizationInterceptor.SESSION_ID_HEADER_KEY;
import static com.calatheas.skeletonj.common.interceptor.OpenApiAuthorizationInterceptor.GW_CLIENT_ID_IN_OPEN_API_HEADER;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class RestdocsUtils {
    private static final String SCHEME = "https";
    private static final String HOST = "skeletonj.calatheas.com";

    public static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme(SCHEME)
                        .host(HOST)
                        .removePort(),
                prettyPrint());
    }

    public static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }

    public static ResponseFieldsSnippet commonResponseFields(FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"))
                .and(descriptors);
    }

    public static ResponseFieldsSnippet commonResponseFields(boolean isCollection, FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지")).and(
                Arrays.stream(descriptors)
                        .map(fd -> {
                            if (fd instanceof SubsectionDescriptor) {
                                if (fd.isOptional()) {
                                    return subsectionWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                                } else {
                                    return subsectionWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                                }
                            } else {
                                if (fd.isOptional()) {
                                    return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                                } else {
                                    return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                                }
                            }
                        })
                        .collect(Collectors.toList()));
    }

    public static ResponseFieldsSnippet commonPageResponseFields(FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                fieldWithPath("data.content[]").type(JsonFieldType.ARRAY).description("리스트"),
                fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN).description("다음페이지 여부")).and(
                Arrays.stream(descriptors)
                        .map(fd -> {
                            if (fd instanceof SubsectionDescriptor) {
                                if (fd.isOptional()) {
                                    return subsectionWithPath(("data.content[].") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                                } else {
                                    return subsectionWithPath(("data.content[].") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                                }
                            } else {
                                if (fd.isOptional()) {
                                    return fieldWithPath(("data.content[].") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                                } else {
                                    return fieldWithPath(("data.content[].") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                                }
                            }
                        })
                        .collect(Collectors.toList()));
    }

    public static MockHttpServletRequestBuilder postWrapper(String url) {
        MockHttpServletRequestBuilder builder = post(url)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder postWrapper(String url, String requestString) {
        MockHttpServletRequestBuilder builder = post(url)
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder postWrapper(String url, String pathVariable, String requestString) {
        MockHttpServletRequestBuilder builder;
        if (StringUtils.hasText(requestString)) {
            builder = post(url, pathVariable)
                    .content(requestString)
                    .contentType(MediaType.APPLICATION_JSON);
        } else {
            builder = post(url, pathVariable)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder putWrapper(String url, String requestString) {
        MockHttpServletRequestBuilder builder = put(url)
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder putWrapper(String url, String pathVariable, String requestString) {
        MockHttpServletRequestBuilder builder;
        if (StringUtils.hasText(requestString)) {
            builder = put(url, pathVariable)
                    .content(requestString)
                    .contentType(MediaType.APPLICATION_JSON);
        } else {
            builder = put(url, pathVariable)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url) {
        MockHttpServletRequestBuilder builder = get(url)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url, String pathVariable) {
        MockHttpServletRequestBuilder builder = get(url, pathVariable)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder deleteWrapper(String url, String pathVariable) {
        MockHttpServletRequestBuilder builder = delete(url, pathVariable)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    private static MockHttpServletRequestBuilder setCustomHeader(String url, MockHttpServletRequestBuilder builder) {
        builder.header("Endpoint", SCHEME + "://" + HOST); // todo 환경변수 MY_URL 로 교체

        if (url.matches("^/openapi.*")) {
            return builder.header(GW_CLIENT_ID_IN_OPEN_API_HEADER, "Client ID when using gateway");
        } else if (url.matches("^/api.*")) {
            return builder.header(SESSION_ID_HEADER_KEY, "Session ID")
                    .header(PARTNER_NID_HEADER_KEY, "Partner NID");
        } else {
            return builder;
        }
    }

    private static ParameterDescriptor setType(ParameterDescriptor parameterDescriptor, String type) {
        parameterDescriptor.attributes(new Attributes.Attribute("type", type));
        return parameterDescriptor;
    }

    public static ParameterDescriptor parameterWithNamAndType(@NonNull String name, @NonNull String type) {
        return setType(parameterWithName(name), type);
    }

    public static <T extends CommonCode> String createCodeLink(Class<T> codeClass, String desc) {
        return String.format("link:code.html#%s[%s, window=\"_blank\"]", codeClass.getSimpleName(), desc);
    }
}
