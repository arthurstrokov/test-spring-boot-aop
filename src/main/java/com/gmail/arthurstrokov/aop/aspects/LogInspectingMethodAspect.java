package com.gmail.arthurstrokov.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class LogInspectingMethodAspect {

    public static final Logger logger = LoggerFactory.getLogger(LogInspectingMethodAspect.class);

    @Around("@annotation(LogInspectingMethod)")
    public Object logInspectingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> cls = Class.forName(joinPoint.getSignature().getDeclaringTypeName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String modifier = Modifier.toString(method.getModifiers());
        Parameter[] parameters = method.getParameters();

        logger.info("Executable Class: {}", cls);
        logger.info("Executable Method: {}", joinPoint);
        logger.info("Executable Method Name: {}", method.getName());
        logger.info("Executable Method Modifier: {}", modifier);
        logger.info("Executable Method Return Type: {}", method.getReturnType());
        logger.info("Executable Method Parameters type: ");
        for (Parameter parameter : parameters) {
            logger.info(" {} {}", parameter.getType().getName(), parameter.getName());
            for (Annotation annotation : parameter.getAnnotations())
                logger.info(" {} ", annotation);
        }
        logger.info("");
        return joinPoint.proceed();
    }
}
