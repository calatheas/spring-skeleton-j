package com.calatheas.skeletonj.common.aspect;

import com.calatheas.skeletonj.common.constant.ConstantBank;
import com.calatheas.skeletonj.common.util.RedisKeyManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Global method calling debounce
 * key : methodName + (optional) arguments hashcode
 */
@Slf4j
@Component
@Aspect
@Order(ConstantBank.METHOD_DEBOUNCE_LOCK_PRECEDENCE)
public class MethodDebounceLockAspect {
    @Autowired
    private MethodDebounceLockService methodDebounceLockService;
    @Autowired
    private RedisKeyManager redisKeyManager;

    @Around("@annotation(com.calatheas.skeletonj.common.aspect.MethodDebounceLock)")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        long lockSec = signature.getMethod().getAnnotation(MethodDebounceLock.class).lockSec();

        String methodName = signature.getMethod().getName();
        StringBuilder key = new StringBuilder(redisKeyManager.generateDebounceLockKey(methodName));

        String[] parameterNames = (signature).getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            key.append(joinPoint.getArgs()[i].hashCode());
        }

        if (methodDebounceLockService.getLock(key.toString(), lockSec)) {
            return joinPoint.proceed();
        } else {
            log.info(String.format("%s call was debounced", methodName));
            return null;
        }
    }
}
