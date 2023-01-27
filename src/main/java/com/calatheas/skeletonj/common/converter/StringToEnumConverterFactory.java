package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.code.CommonCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;
import java.util.EnumSet;

public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter(targetType);
    }

    private final class StringToEnumConverter<E extends Enum> implements Converter<String, E> {
        private Class<E> clz;
        private boolean isCommonCode = false;

        public StringToEnumConverter(Class<E> enumClass) {
            this.clz = enumClass;

            if (Arrays.stream(enumClass.getInterfaces()).anyMatch(it -> it.equals(CommonCode.class))) {
                isCommonCode = true;
            }
        }

        @Override
        public E convert(String source) {
            if (source == null) {
                return null;
            } else if (!isCommonCode) {
                return (E) Enum.valueOf(clz, source);
            } else {
                return (E) EnumSet.allOf(clz).stream()
                        .filter(e -> ((CommonCode) e).getCode().equals(source))
                        .findAny()
                        .orElseThrow();
            }
        }
    }
}
