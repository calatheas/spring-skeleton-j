package com.calatheas.skeletonj.common.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyManager {
    @Value("${spring.redis.key-prefix}")
    @Getter
    private String prefix;
    private final static String KEY_PAYMENT_ID = "pid";
    private final static String KEY_SETTLEMENT_ID = "sid";
    private final static String KEY_LOCK = "lock";
    private final static String KEY_DEBOUNCE = "lock";
    private final static String DELIMITER = ":";

    public String generatePaymentIdKey(String date) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_PAYMENT_ID)
                .append(DELIMITER)
                .append(date)
                .toString();
    }

    public String generateSettlementIdKey(String date) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_SETTLEMENT_ID)
                .append(DELIMITER)
                .append(date)
                .toString();
    }

    public String generateLockKey(String type) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_LOCK)
                .append(DELIMITER)
                .append(type)
                .toString();
    }

    public String generateLockKey(String type, String key) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_LOCK)
                .append(DELIMITER)
                .append(type)
                .append(DELIMITER)
                .append(key)
                .toString();
    }

    public String generateDebounceLockKey(String methodName) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_DEBOUNCE)
                .append(DELIMITER)
                .append(methodName)
                .toString();
    }
}
