package com.calatheas.skeletonj.common.config;

import com.calatheas.skeletonj.common.model.AsyncSession;
import com.calatheas.skeletonj.common.model.GlobalSession;
import com.calatheas.skeletonj.common.session.domain.Session;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class ClonedTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> callerThreadContext = MDC.getCopyOfContextMap();
        Session session = GlobalSession.getSession();

        return () -> {
            try {
                MDC.setContextMap(callerThreadContext);
                AsyncSession.setSession(session);
                runnable.run();
            } finally {
                AsyncSession.clear();
            }
        };
    }
}
