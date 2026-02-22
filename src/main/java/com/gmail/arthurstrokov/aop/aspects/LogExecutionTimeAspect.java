package com.gmail.arthurstrokov.aop.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("com.gmail.arthurstrokov.aop.aspects.PointcutDefinitions.logExecutionTimePointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("{} executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());
        }
    }
}
