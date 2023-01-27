package com.calatheas.skeletonj;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class SkeletonjApplicationTest {

    @Test
    void contextLoads() {
        System.getenv().forEach(
                (key, value) -> System.out.println(key+"|"+value)
        );
    }

}
