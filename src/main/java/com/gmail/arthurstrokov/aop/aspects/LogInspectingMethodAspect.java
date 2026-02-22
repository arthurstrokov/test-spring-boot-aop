package com.gmail.arthurstrokov.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

@Slf4j
@Aspect
@Component
public class LogInspectingMethodAspect {

    @Around("com.gmail.arthurstrokov.aop.aspects.PointcutDefinitions.logInspectingMethodPointcut()")
    public Object logInspectingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringType = method.getDeclaringClass();
        String modifier = Modifier.toString(method.getModifiers());
        Parameter[] parameters = method.getParameters();

        log.info("Executable Class: {}", declaringType.getName());
        log.info("Executable Method Signature: {}", signature.toShortString());
        log.info("Executable Method Name: {}", method.getName());
        log.info("Executable Method Modifier: {}", modifier);
        log.info("Executable Method Return Type: {}", method.getReturnType().getSimpleName());

        if (parameters.length > 0) {
            log.info("Executable Method Parameters:");
            for (Parameter parameter : parameters) {
                log.info(" - {} {}", parameter.getType().getSimpleName(), parameter.getName());
                for (Annotation annotation : parameter.getAnnotations()) {
                    log.info("   Annotation: {}", annotation);
                }
            }
        }
        return joinPoint.proceed();
    }
}
