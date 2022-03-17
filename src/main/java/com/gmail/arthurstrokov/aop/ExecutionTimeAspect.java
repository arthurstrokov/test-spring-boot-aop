package com.gmail.arthurstrokov.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
This is the class where we will implement the logic that we want our custom annotation to inject.
 */
@Aspect
@Component
public class ExecutionTimeAspect {

    public static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    /*
    First, we have annotated our method with @Around.
    This is our advice, and around advice means we are adding extra code both before and after method execution.
    There are other types of advice, such as before and after but they will be left out of scope for this article.

    Next, our @Around annotation has a point cut argument.
    Our pointcut just says, ‘Apply this advice any method which is annotated with @LogExecutionTime.'
    There are lots of other types of pointcuts, but they will again be left out if scope.

    The method logExecutionTime() itself is our advice. There is a single argument, ProceedingJoinPoint.
    In our case, this will be an executing method which has been annotated with @LogExecutionTime.

    Finally, when our annotated method ends up being called, what will happen is our advice will be called first.
    Then it's up to our advice to decide what to do next.
    In our case, our advice is doing nothing other than calling proceed(),
     which is the just calling the original annotated method.
     */
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}
