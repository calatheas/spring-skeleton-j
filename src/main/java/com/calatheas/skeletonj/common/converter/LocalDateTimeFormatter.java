package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.constant.ConstantBank;
import org.springframework.format.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String text, Locale locale) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_TIME_FORMAT));
    }

    @Override
    public String print(LocalDateTime obj, Locale locale) {
        return DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_TIME_FORMAT).format(obj);
    }
}
