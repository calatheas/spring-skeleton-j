package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.encrypt.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ByteEncryptConverter implements AttributeConverter<String, byte[]> {
    @Autowired
    private EncryptUtil encryptUtil;

    @Override
    public byte[] convertToDatabaseColumn(String attribute) {
        if (attribute == null)
            return null;

        return encryptUtil.encryptData(attribute);
    }

    @Override
    public String convertToEntityAttribute(byte[] dbData) {
        if (dbData == null)
            return null;

        return encryptUtil.decryptData(dbData);
    }
}
