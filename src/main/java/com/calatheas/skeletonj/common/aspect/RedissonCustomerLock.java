package com.calatheas.skeletonj.common.aspect;

import com.calatheas.skeletonj.common.constant.ConstantBank;
import com.calatheas.skeletonj.common.exception.BusinessException;
import com.calatheas.skeletonj.common.exception.ExceptionType;
import com.calatheas.skeletonj.common.util.RedisKeyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
@Order(ConstantBank.CUSTOMER_LOCK_PRECEDENCE)
public class RedissonCustomerLock {

    public static final String LOCK_TYPE = "cusotmer";
    @Autowired
    private RedisKeyManager redisKeyManager;
    private final RedissonClient redissonClient;


    @Around("@annotation(com.calatheas.skeletonj.common.aspect.CustomerLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CustomerLock customerLock = method.getAnnotation(CustomerLock.class);

        String key = createKey(joinPoint.getArgs());
        if (StringUtils.isEmpty(key)) {
            log.info("RedissonLock Customer Lockable false method : {}", method.getName());
            return joinPoint.proceed();
        }

        RLock lock = redissonClient.getLock(key);
        try {
            boolean locked = lock.tryLock(customerLock.waitTime(), customerLock.leaseTime(), customerLock.timeUnit());
            if (!locked) {
                log.info("RedissonLock Customer Locked fail {}", key);
            }
            log.info("RedissonLock Customer Locked!! : {}", key);
            return joinPoint.proceed();
        } catch (InterruptedException ie) {
            log.error("RedissonLock Customer TryLock InterruptedException", ie);
            throw BusinessException.of(ExceptionType.DISABLED_REDISSON_CUSTOMER_LOCK);
        } finally {
            unlock(lock);
            log.info("RedissonLock Customer unlocked!! : {}", key);
        }
    }

    private void unlock(RLock lock) {
        if (lock != null && lock.isLocked()) {
            lock.unlock();
        } else {
            log.error("RedissonLock Customer UnLock Disabled");
        }
    }

    private String createKey(Object[] args) {
        String resultKey = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof CustomerLockable) {
                Long customerNid = ((CustomerLockable) args[i]).getCustomerNid();
                if (customerNid == null) {
                    resultKey = redisKeyManager.generateLockKey(LOCK_TYPE);
                } else {
                    resultKey = redisKeyManager.generateLockKey(LOCK_TYPE, customerNid.toString());
                }
                break;
            } else if (args[i] instanceof Collection) {
                if (!((Collection) args[i]).isEmpty()) {
                    Object firstObject = ((List) args[i]).get(0);
                    if (firstObject instanceof CustomerLockable) {
                        Long customerNid = ((CustomerLockable) firstObject).getCustomerNid();
                        if (customerNid == null) {
                            resultKey = redisKeyManager.generateLockKey(LOCK_TYPE);
                        } else {
                            resultKey = redisKeyManager.generateLockKey(LOCK_TYPE, customerNid.toString());
                        }
                        break;
                    }
                }
            }
        }
        return resultKey;
    }
}
