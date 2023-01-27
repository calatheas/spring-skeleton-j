package com.calatheas.skeletonj.common.config;

import com.calatheas.skeletonj.common.code.Profile;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class RedisConfig {
    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.url}")
    private String url;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();

        if (Profile.fromCode(activeProfile).isLocalEnvironment()) {
            config.useSingleServer().setAddress(getAddress());
        } else {
            config.useClusterServers().setNodeAddresses(List.of(getAddress()));
        }

        return Redisson.create(config);
    }

    private String getAddress() {
        return String.format("redis://%s:%s", url, port);
    }
}
