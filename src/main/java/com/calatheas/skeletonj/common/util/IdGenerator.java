package com.calatheas.skeletonj.common.util;

import com.calatheas.skeletonj.common.constant.ConstantBank;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class IdGenerator {
    private static int SIZE_RANDOM_CHAR = 8;

    @Autowired
    private RedisKeyManager redisKeyManager;
    @Autowired
    private RedissonClient redissonClient;

    private final static String PAYMENT_ID_MID = "P";
    private final static String SETTLEMENT_ID_MID = "S";

    private String generateId(@NonNull String middleWord) {
        String randomNumericString = null;
        String transactionDate = DateTimeFormatter.ofPattern(ConstantBank.LOCAL_DATE_DB_FORMAT).format(LocalDate.now());

        for (int i=0; i<Integer.MAX_VALUE; i++) {
            randomNumericString = RandomStringUtils.randomNumeric(SIZE_RANDOM_CHAR);

            String key = middleWord.equals(PAYMENT_ID_MID) ? redisKeyManager.generatePaymentIdKey(transactionDate) : redisKeyManager.generateSettlementIdKey(transactionDate);

            RSet<String> set = redissonClient.getSet(key);
            if (set.contains(randomNumericString)) {
                continue;
            } else {
                set.expire(1, TimeUnit.DAYS);
                set.add(randomNumericString);
                break;
            }
        }
        return transactionDate+middleWord+randomNumericString;
    }

    public String generatePaymentId() {
        return generateId(PAYMENT_ID_MID);
    }

    public String generateSettlementId() {
        return generateId(SETTLEMENT_ID_MID);
    }
}
