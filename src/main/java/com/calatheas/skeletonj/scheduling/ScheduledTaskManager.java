package com.calatheas.skeletonj.scheduling;

import com.calatheas.skeletonj.common.aspect.MethodDebounceLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 임시 스케줄링 작업 서비스
 */
@Slf4j
@Component
public class ScheduledTaskManager {

    // 일별 집계
    @Scheduled(cron = "0 0 1 1/1 * ?")
    @MethodDebounceLock
    public void aggregatePoint() {
        LocalDate aggregateDay = LocalDate.now().minusDays(1);

        try {
            log.info("[Scheduled][aggregatePoint] - start {}", aggregateDay);
            log.info("[Scheduled][aggregatePoint] - end");
        } catch (Exception e) {
            log.error("[Scheduled][aggregatePoint] - Error occurred ({})", aggregateDay);
        }
    }
}
