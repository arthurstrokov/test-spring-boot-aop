package com.gmail.arthurstrokov.aop.proxy;

import com.gmail.arthurstrokov.aop.proxy.repository.UserRepository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class UserRepositoryInvocationHandler implements InvocationHandler {

    private final UserRepository repository = new UserRepository();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method.getName() + " was called with args " + Arrays.toString(args));
        return method.invoke(repository, args);
    }
}
