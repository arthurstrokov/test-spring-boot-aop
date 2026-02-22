package com.gmail.arthurstrokov.aop.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тесты для демонстрации использования CGLib (через Spring ProxyFactory).
 * <p>
 * Основные характеристики CGLib:
 * 1. Позволяет проксировать классы, не реализующие интерфейсы.
 * 2. Создает динамический подкласс целевого класса на лету.
 * 3. Spring Boot использует CGLib по умолчанию (через ProxyFactory).
 * 4. Не может проксировать final-классы или final-методы.
 */
class CglibDynamicProxyDemoTest {

    @Test
    void testCglibDynamicProxy() {
        System.out.println("--- Старт теста: Базовый CGLib (Spring ProxyFactory) ---");
        // Создаем прокси для класса UserService
        UserService userService = createProxyWithSpringAop(new UserService());

        // Вызов метода пройдет через перехватчик (advice)
        userService.saveUser("John", 25);

        // Тестируем получение
        Object result = userService.getUser(1);
        assertNotNull(result);

        // Тестируем удаление
        userService.deleteUser(1);
        System.out.println("--- Конец теста: Базовый CGLib (Spring ProxyFactory) ---\n");
    }

    @Test
    void testCglibDynamicProxyWithLogging() {
        System.out.println("--- Старт теста: CGLib с логированием ---");
        // Создаем прокси с логированием до и после выполнения
        UserService userService = createProxyWithLogging(new UserService());

        userService.saveUser("Jane", 30);
        assertNotNull(userService.getUser(1));
        System.out.println("--- Конец теста: CGLib с логированием ---\n");
    }

    @Test
    void testCglibFilteredProxy() {
        System.out.println("--- Старт теста: CGLib с фильтрацией методов ---");
        // Прокси, который перехватывает только методы, начинающиеся на "save"
        UserService filteredProxy = createFilteredProxy(new UserService());

        // Метод saveUser — БУДЕТ логироваться
        filteredProxy.saveUser("Alice", 20);

        // Метод getUser — НЕ БУДЕТ логироваться дополнительно
        assertNotNull(filteredProxy.getUser(1));
        System.out.println("--- Конец теста: CGLib с фильтрацией методов ---\n");
    }

    /**
     * Создание прокси через Spring ProxyFactory.
     * Это стандартный способ работы с динамическими прокси в экосистеме Spring.
     */
    @SuppressWarnings("unchecked")
    private <T> T createProxyWithSpringAop(T target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.setProxyTargetClass(true); // Принудительно используем CGLib (проксирование класса)

        // Добавляем пустой перехватчик, который просто пробрасывает вызов
        proxyFactory.addAdvice(new org.aopalliance.intercept.MethodInterceptor() {
            @Override
            public Object invoke(org.aopalliance.intercept.MethodInvocation invocation) throws Throwable {
                return invocation.proceed();
            }
        });

        return (T) proxyFactory.getProxy();
    }

    /**
     * Создание CGLib прокси с логированием.
     */
    @SuppressWarnings("unchecked")
    private <T> T createProxyWithLogging(T target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.setProxyTargetClass(true);

        proxyFactory.addAdvice(new org.aopalliance.intercept.MethodInterceptor() {
            @Override
            public Object invoke(org.aopalliance.intercept.MethodInvocation invocation) throws Throwable {
                Method method = invocation.getMethod();
                System.out.println("[CGLib LOG] До вызова метода: " + method.getName());
                System.out.println("[CGLib LOG] Аргументы: " + Arrays.toString(invocation.getArguments()));

                long startTime = System.currentTimeMillis();

                // Выполнение оригинального метода
                Object result = invocation.proceed();

                long endTime = System.currentTimeMillis();

                System.out.println("[CGLib LOG] После вызова метода: " + method.getName() + ", время: " + (endTime - startTime) + "мс");
                return result;
            }
        });

        return (T) proxyFactory.getProxy();
    }

    /**
     * Создание фильтрованного прокси — логируем только определенные методы.
     */
    @SuppressWarnings("unchecked")
    private <T> T createFilteredProxy(T target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.setProxyTargetClass(true);

        proxyFactory.addAdvice(new org.aopalliance.intercept.MethodInterceptor() {
            @Override
            public Object invoke(org.aopalliance.intercept.MethodInvocation invocation) throws Throwable {
                Method method = invocation.getMethod();

                // Простая фильтрация по имени метода
                if (method.getName().startsWith("save")) {
                    System.out.println("[CGLib Filtered LOG] Перехвачен метод записи: " + method.getName());
                    return invocation.proceed();
                }

                // Остальные методы выполняются без дополнительной логики
                return invocation.proceed();
            }
        });

        return (T) proxyFactory.getProxy();
    }
}
