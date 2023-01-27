package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.util.CommonBeans;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public class MapToStringConverter implements AttributeConverter<Map<String, Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        if (attribute != null) {
            try {
                return CommonBeans.objectMapper.writeValueAsString(attribute);
            } catch (JsonProcessingException e) {
                throw BusinessException.of(ExceptionType.UNEXPECTED).setMessage("Json 객체의 문자열 변환에 실패하였습니다.");
            }
        }

        return null;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            try {
                return CommonBeans.objectMapper.readValue(dbData, new TypeReference<HashMap<String, Object>>() {});
            } catch (JsonProcessingException e) {
                throw BusinessException.of(ExceptionType.UNEXPECTED).setMessage("문자열의 JSON 객체 변환에 실패하였습니다.");
            }
        }

        return null;
    }
}
