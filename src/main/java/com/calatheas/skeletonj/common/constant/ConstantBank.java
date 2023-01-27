package com.calatheas.skeletonj.common.constant;

public class ConstantBank {
    public final static String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
    public final static String LOCAL_DATE_DB_FORMAT = "yyyyMMdd";

    /**
     * @Ordered 는 낮아질수록 순위가 높음,
     * @transactional 이 Ordered.LOWEST_PRECEDENCE (=Integer.MAX_VALUE)
     */
    public static final int METHOD_DEBOUNCE_LOCK_PRECEDENCE = 100;
    public static final int CUSTOMER_LOCK_PRECEDENCE = 99;
    public static final int PARTNER_LOCK_PRECEDENCE = 98;
}
