package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum BankAccountTransactionType implements CommonCode {

    WITHDRAW("WITHDRAW", "출금"),
    DEPOSIT("DEPOSIT", "입금");

    private String code;
    private String desc;
}
