package com.calatheas.skeletonj.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CommonBeans {
    public static MessageUtil messageUtil;
    public static ObjectMapper objectMapper;
    public static IdGenerator idGenerator;
    public static String myUrl;

    private final MessageUtil messageUtilBean;
    private final ObjectMapper objectMapperBean;
    private final IdGenerator idGeneratorBean;

    @Value("${internal-service.my.url}")
    private String myUrlBean;

    @PostConstruct
    private void initialize() {
        messageUtil = messageUtilBean;
        objectMapper = objectMapperBean;
        myUrl = myUrlBean;
        idGenerator = idGeneratorBean;
    }
}
