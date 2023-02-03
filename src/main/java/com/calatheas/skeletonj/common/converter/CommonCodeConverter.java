package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.code.BankCode;
import com.calatheas.skeletonj.common.code.CommonCode;
import com.calatheas.skeletonj.common.code.ItemCode;
import com.calatheas.skeletonj.common.code.RoundingMode;
import com.calatheas.skeletonj.common.code.Yn;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class CommonCodeConverter<E extends Enum<E> & CommonCode> implements AttributeConverter<E, String> {
    private Class<E> clz;

    CommonCodeConverter(Class<E> enumClass) {
        this.clz = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if (attribute == null) return null;

        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return EnumSet.allOf(clz).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}

@Converter(autoApply = true)
class YnConverter extends CommonCodeConverter<Yn> {
    YnConverter() {
        super(Yn.class);
    }
}

@Converter(autoApply = true)
class ItemCodeConverter extends CommonCodeConverter<ItemCode> {
    ItemCodeConverter() {
        super(ItemCode.class);
    }
}

@Converter(autoApply = true)
class BankCodeConverter extends CommonCodeConverter<BankCode> {
    BankCodeConverter() {
        super(BankCode.class);
    }
}

@Converter(autoApply = true)
class RoundingModeConverter extends CommonCodeConverter<RoundingMode> {
    RoundingModeConverter() {
        super(RoundingMode.class);
    }
}
