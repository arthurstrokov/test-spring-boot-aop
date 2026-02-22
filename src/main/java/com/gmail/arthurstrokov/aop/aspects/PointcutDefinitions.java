package com.gmail.arthurstrokov.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Класс для хранения общих определений Pointcut.
 * Реализует принцип DRY, позволяя переиспользовать определения Pointcut в разных аспектах.
 */
public class PointcutDefinitions {

    /**
     * Методы, помеченные аннотацией {@link com.gmail.arthurstrokov.aop.annotations.LogExecutionTime}
     */
    @Pointcut("@annotation(com.gmail.arthurstrokov.aop.annotations.LogExecutionTime)")
    public void logExecutionTimePointcut() {
    }

    /**
     * Методы, помеченные аннотацией {@link com.gmail.arthurstrokov.aop.annotations.LogInspectingMethod}
     */
    @Pointcut("@annotation(com.gmail.arthurstrokov.aop.annotations.LogInspectingMethod)")
    public void logInspectingMethodPointcut() {
    }

    /**
     * Методы, помеченные аннотацией {@link com.gmail.arthurstrokov.aop.annotations.LogException}
     */
    @Pointcut("@annotation(com.gmail.arthurstrokov.aop.annotations.LogException)")
    public void logExceptionPointcut() {
    }
}
