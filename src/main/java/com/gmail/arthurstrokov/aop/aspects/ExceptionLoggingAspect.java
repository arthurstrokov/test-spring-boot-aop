package com.gmail.arthurstrokov.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {

    @AfterThrowing(pointcut = "@annotation(com.gmail.arthurstrokov.aop.annotations.LogException)", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in method: {} with message: {}", 
                joinPoint.getSignature().toShortString(), 
                ex.getMessage(), 
                ex);
    }
}
