package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.constant.ConstantBank;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_FORMAT));
    }

    @Override
    public String print(LocalDate obj, Locale locale) {
        return DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_FORMAT).format(obj);
    }
}
