package com.calatheas.skeletonj.common.converter;

import com.calatheas.skeletonj.common.code.BankAccountTransactionType;
import com.calatheas.skeletonj.common.code.BankAccountType;
import com.calatheas.skeletonj.common.code.BankCode;
import com.calatheas.skeletonj.common.code.BankRequestReasonType;
import com.calatheas.skeletonj.common.code.CommonCode;
import com.calatheas.skeletonj.common.code.ExpirationPeriodType;
import com.calatheas.skeletonj.common.code.OutNameType;
import com.calatheas.skeletonj.common.code.PartnerType;
import com.calatheas.skeletonj.common.code.PointActivationLevel;
import com.calatheas.skeletonj.common.code.PointReasonCode;
import com.calatheas.skeletonj.common.code.PointRequestStatus;
import com.calatheas.skeletonj.common.code.PointTransactionStatus;
import com.calatheas.skeletonj.common.code.PointTransactionType;
import com.calatheas.skeletonj.common.code.PointType;
import com.calatheas.skeletonj.common.code.PointUsageType;
import com.calatheas.skeletonj.common.code.PointWalletStatus;
import com.calatheas.skeletonj.common.code.PointWalletType;
import com.calatheas.skeletonj.common.code.RoundingMode;
import com.calatheas.skeletonj.common.code.SettlementItemClass;
import com.calatheas.skeletonj.common.code.SettlementStatus;
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
class ExpirationPeriodTypeConverter extends CommonCodeConverter<ExpirationPeriodType> {
    ExpirationPeriodTypeConverter() {
        super(ExpirationPeriodType.class);
    }
}

@Converter(autoApply = true)
class PartnerTypeConverter extends CommonCodeConverter<PartnerType> {
    PartnerTypeConverter() {
        super(PartnerType.class);
    }
}

@Converter(autoApply = true)
class PointTypeConverter extends CommonCodeConverter<PointType> {
    PointTypeConverter() {
        super(PointType.class);
    }
}

@Converter(autoApply = true)
class PointUsageTypeConverter extends CommonCodeConverter<PointUsageType> {
    PointUsageTypeConverter() {
        super(PointUsageType.class);
    }
}

@Converter(autoApply = true)
class SettlementItemClassConverter extends CommonCodeConverter<SettlementItemClass> {
    SettlementItemClassConverter() {
        super(SettlementItemClass.class);
    }
}

@Converter(autoApply = true)
class YnConverter extends CommonCodeConverter<Yn> {
    YnConverter() {
        super(Yn.class);
    }
}

@Converter(autoApply = true)
class PointWalletTypeConverter extends CommonCodeConverter<PointWalletType> {
    PointWalletTypeConverter() {
        super(PointWalletType.class);
    }
}

@Converter(autoApply = true)
class PointReasonCodeConverter extends CommonCodeConverter<PointReasonCode> {
    PointReasonCodeConverter() {
        super(PointReasonCode.class);
    }
}

@Converter(autoApply = true)
class PointActivationLevelConverter extends CommonCodeConverter<PointActivationLevel> {
    PointActivationLevelConverter() {
        super(PointActivationLevel.class);
    }
}

@Converter(autoApply = true)
class PointRequestStatusConverter extends CommonCodeConverter<PointRequestStatus> {
    PointRequestStatusConverter() {
        super(PointRequestStatus.class);
    }
}

@Converter(autoApply = true)
class PointTransactionStatusConverter extends CommonCodeConverter<PointTransactionStatus> {
    PointTransactionStatusConverter() {
        super(PointTransactionStatus.class);
    }
}

@Converter(autoApply = true)
class PointWalletStatusConverter extends CommonCodeConverter<PointWalletStatus> {
    PointWalletStatusConverter() {
        super(PointWalletStatus.class);
    }
}

@Converter(autoApply = true)
class BankCodeConverter extends CommonCodeConverter<BankCode> {
    BankCodeConverter() {
        super(BankCode.class);
    }
}

@Converter(autoApply = true)
class BankAccountTransactionTypeConverter extends CommonCodeConverter<BankAccountTransactionType> {
    BankAccountTransactionTypeConverter() {
        super(BankAccountTransactionType.class);
    }
}

@Converter(autoApply = true)
class BankRequestReasonTypeConverter extends CommonCodeConverter<BankRequestReasonType> {
    BankRequestReasonTypeConverter() {
        super(BankRequestReasonType.class);
    }
}

@Converter(autoApply = true)
class PointTransactionTypeConverter extends CommonCodeConverter<PointTransactionType> {
    PointTransactionTypeConverter() {
        super(PointTransactionType.class);
    }
}

@Converter(autoApply = true)
class SettlementStatusConverter extends CommonCodeConverter<SettlementStatus> {
    SettlementStatusConverter() {
        super(SettlementStatus.class);
    }
}

@Converter(autoApply = true)
class RoundingModeConverter extends CommonCodeConverter<RoundingMode> {
    RoundingModeConverter() {
        super(RoundingMode.class);
    }
}

@Converter(autoApply = true)
class OutNameTypeConverter extends CommonCodeConverter<OutNameType> {
    OutNameTypeConverter() {
        super(OutNameType.class);
    }
}

@Converter(autoApply = true)
class BankAccountTypeConverter extends CommonCodeConverter<BankAccountType> {
    BankAccountTypeConverter() {
        super(BankAccountType.class);
    }
}
