package com.calatheas.skeletonj.common.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyManager {
    @Value("${spring.redis.key-prefix}")
    @Getter
    private String prefix;

    @Value(value = "${spring.redis.session.ttl}")
    @Getter
    private int sessionTtl;

    @Value(value = "${spring.redis.temp-session.ttl}")
    @Getter
    private long temporarySessionTtl;

    public final static String KEY_ADMIN = "admin:";
    private final static String KEY_TEMPORARY_SESSION = "temp-session";
    private final static String KEY_WALLET_LOCK = "wallet";
    private final static String KEY_PAYMENT_ID = "pid";
    private final static String KEY_SETTLEMENT_ID = "sid";
    private final static String KEY_SCHEDULED = "shed";
    private final static String KEY_LOCK = "lock";
    private final static String KEY_DEBOUNCE = "lock";
    private final static String DELIMITER = ":";

    public String generatePointWalletLockKey(String id) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_WALLET_LOCK)
                .append(DELIMITER)
                .append(id)
                .toString();
    }

    public String generateSessionKey(String key) {
        return KEY_ADMIN + key;
    }

    public String generateTemporarySessionKey(String key) {
        return new StringBuilder(prefix)
                .append(":")
                .append(KEY_TEMPORARY_SESSION)
                .append(":")
                .append(key)
                .toString();
    }

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

    public String generateScheduledTaskLockKey(String methodName) {
        return new StringBuilder()
                .append(prefix)
                .append(DELIMITER)
                .append(KEY_SCHEDULED)
                .append(DELIMITER)
                .append(methodName)
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
