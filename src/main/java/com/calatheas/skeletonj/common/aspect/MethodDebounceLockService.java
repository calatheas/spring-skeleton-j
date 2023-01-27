package com.calatheas.skeletonj.common.aspect;

import com.calatheas.skeletonj.common.util.RedisKeyManager;
import lombok.NonNull;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MethodDebounceLockService {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisKeyManager redisKeyManager;

    public boolean getLock(@NonNull String key, long lockSec) {
        RBucket<Boolean> bucket = redissonClient.getBucket(key);
        return bucket.trySet(Boolean.TRUE, lockSec, TimeUnit.SECONDS);
    }

    public boolean getLock(@NonNull String methodName, @NonNull String id, long lockSec) {
        StringBuilder key = new StringBuilder(redisKeyManager.generateDebounceLockKey(methodName));
        key.append(id);

        RBucket<Boolean> bucket = redissonClient.getBucket(key.toString());
        return bucket.trySet(Boolean.TRUE, lockSec, TimeUnit.SECONDS);
    }
}
