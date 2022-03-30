package com.gmail.arthurstrokov.aop.proxy;

import com.gmail.arthurstrokov.aop.proxy.repository.Repository;
import com.gmail.arthurstrokov.aop.proxy.repository.UserRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Class<?> userRepositoryClass = UserRepository.class;
//        UserRepository repository = new UserRepository();
        Repository repositoryObject = (Repository) Proxy.newProxyInstance(userRepositoryClass.getClassLoader(), userRepositoryClass.getInterfaces(), new UserRepositoryInvocationHandler());

        repositoryObject.save(new Object());

        repositoryObject.get(0);


//        System.out.println("Hello World");

//        User user = new User();
//
//        Class<?> aClass = Class.forName("org.example.User");
//        Class<? extends User> aClass1 = user.getClass();//
//
//        Method[] methods = aClass.getMethods();
//        System.out.println(Arrays.toString(methods));
//
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        System.out.println(Arrays.toString(declaredMethods));
//
//        Field[] declaredFields = aClass.getDeclaredFields();
//
//        Method method = aClass.getDeclaredMethod("setName", String.class);
//
//        method.setAccessible(true);
//
//        System.out.println(user);
//        method.invoke(user, "Name");
//        System.out.println(user);
//
//        Field field = aClass.getDeclaredField("name");
//        field.setAccessible(true);
//
//        System.out.println(user);
//        field.set(user, "New Name");
//        System.out.println(user);
//
//        Class<?> superclass = aClass.getSuperclass();
//        Method[] currentFields = superclass.getDeclaredMethods();
//        System.out.println(Arrays.toString(currentFields));
//        System.out.println(superclass);
//
//        field.setAccessible(false);
//
//        method.setAccessible(false);
    }
}
