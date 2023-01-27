package com.calatheas.skeletonj.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource messageSource;

    public String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String messageCode, @Nullable Object... args) {
        return messageSource.getMessage(messageCode, args, LocaleContextHolder.getLocale());
    }
}
