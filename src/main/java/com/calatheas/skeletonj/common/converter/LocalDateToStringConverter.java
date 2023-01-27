package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.constant.ConstantBank;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateToStringConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        if (attribute == null)
            return null;

        return DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_DB_FORMAT).format(attribute);
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;

        return LocalDate.parse(dbData, DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_DB_FORMAT));
    }
}
