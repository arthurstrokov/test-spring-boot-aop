package com.gmail.arthurstrokov.aop.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

/**
 * @author Артур Александрович Строков
 * @email astrokov@clevertec.ru
 * @created 03.11.2022
 */
@Slf4j
public class MethodInterceptorImpl implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Hello from MethodInterceptorImpl");
        log.info("Method {} was called with args {}", invocation.getMethod(), Arrays.toString(invocation.getArguments()));
        return invocation.proceed();
    }
}
