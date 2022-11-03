package com.gmail.arthurstrokov.aop.proxy;

import com.gmail.arthurstrokov.aop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class UserRepositoryInvocationHandler implements InvocationHandler {

    private final UserRepository repository = new UserRepository();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Hello from UserRepositoryInvocationHandler");
        log.info("Method {} was called with args {}", method.getName(), Arrays.toString(args));
        return method.invoke(repository, args);
    }
}
