package com.calatheas.skeletonj.code.service;

import com.calatheas.skeletonj.code.service.model.BaseCode;
import com.calatheas.skeletonj.code.service.model.CodeQuery;
import com.calatheas.skeletonj.common.annotation.CodeClass;
import com.calatheas.skeletonj.common.code.CommonCode;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CodeQueryService {
    private static final CodeQuery codeQuery = createCodeQuery();

    private static CodeQuery createCodeQuery() {
        // 직접 package 입력되지 않아 workaround (참고 - https://github.com/ronmamo/reflections/issues/373)
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(CommonCode.class.getPackageName()))
                .addScanners(Scanners.TypesAnnotated)
        );

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CodeClass.class);

        log.debug("Target classes - {}",
                String.join(", ", classes.stream().map(Class::getName).collect(Collectors.toList()))
        );

        log.debug("Not enum classes - {}",
                String.join(", ", classes.stream().filter(it -> !it.isEnum()).map(Class::getSimpleName).collect(Collectors.toList()))
        );

        log.debug("Common code classes - {}",
                classes.stream().filter(clazz -> Arrays.stream(clazz.getInterfaces()).anyMatch(it -> it.equals(CommonCode.class))).map(Class::getSimpleName).collect(Collectors.toList())
        );

        return CodeQuery.of(classes
                .stream()
                .filter(Class::isEnum)
                .filter(clazz -> Arrays.stream(clazz.getInterfaces()).anyMatch(it -> it.equals(CommonCode.class)))
                .collect(Collectors.toMap(
                        it -> it.getSimpleName(),
                        it -> getCodeSet(it, getFactoryMethod(it.getAnnotation(CodeClass.class))))));
    }

    private static Set<BaseCode> getCodeSet(Class enumClass, Function of) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(it -> (BaseCode) of.apply(it))
                .collect(Collectors.toSet());
    }

    private static Function getFactoryMethod(CodeClass codeClass) {
        return (it) -> {
            try {
                // static mathod 이므로 첫번째 null
                return codeClass.value().getMethod(codeClass.methodName(), CommonCode.class).invoke(null, it);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                log.error("Reflection errors {}", e);
                return null;
            }
        };
    }

    public CodeQuery getCodeQuery() {
        return codeQuery;
    }
}
