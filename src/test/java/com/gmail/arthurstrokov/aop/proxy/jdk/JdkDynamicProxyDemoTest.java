package com.gmail.arthurstrokov.aop.proxy.jdk;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тесты для демонстрации использования JDK Dynamic Proxy.
 * <p>
 * Основные характеристики:
 * 1. Встроен в стандартную библиотеку Java (Reflection API).
 * 2. Работает только через интерфейсы.
 * 3. Позволяет перехватывать вызовы методов и добавлять кастомную логику (например, логирование).
 * 4. Для создания используется метод Proxy.newProxyInstance().
 */
class JdkDynamicProxyDemoTest {

    @Test
    void testJdkDynamicProxy() {
        System.out.println("--- Старт теста: Базовый JDK Proxy ---");
        // Создаем прокси для реализации интерфейса
        UserRepositoryInterface userRepository = createProxy(new UserRepositoryImpl());

        // Тестируем метод save. Вызов пройдет через прокси, затем в оригинальный объект.
        userRepository.save("John");

        // Тестируем метод get
        Object result = userRepository.get(0);
        assertNotNull(result);

        // Тестируем метод delete
        userRepository.delete(0);
        System.out.println("--- Конец теста: Базовый JDK Proxy ---\n");
    }

    @Test
    void testJdkDynamicProxyWithLogging() {
        System.out.println("--- Старт теста: JDK Proxy с логированием ---");
        // Создаем прокси, который добавляет логирование до и после вызова методов
        UserRepositoryInterface userRepository = createProxyWithLogging(new UserRepositoryImpl());

        // Вызываем методы. В консоли будет видно сообщение от прокси и время выполнения.
        userRepository.save("Test");
        assertNotNull(userRepository.get(0));
        System.out.println("--- Конец теста: JDK Proxy с логированием ---\n");
    }

    /**
     * Создание простого JDK Dynamic Proxy.
     *
     * @param target Оригинальный объект, который нужно проксировать.
     * @return Прокси-объект, реализующий те же интерфейсы, что и target.
     */
    @SuppressWarnings("unchecked")
    private <T> T createProxy(T target) {
        // Получаем список интерфейсов оригинального объекта
        Class<?>[] interfaces = target.getClass().getInterfaces();

        // InvocationHandler описывает логику обработки вызовов методов
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // В простейшем случае — просто делегируем вызов оригинальному объекту
                return method.invoke(target, args);
            }
        };

        // Создаем экземпляр прокси
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // Загрузчик классов
                interfaces,                        // Интерфейсы, которые должен реализовать прокси
                handler                            // Обработчик вызовов
        );
    }

    /**
     * Создание JDK Dynamic Proxy с дополнительной логикой логирования.
     *
     * @param target Оригинальный объект.
     * @return Прокси-объект с логированием.
     */
    @SuppressWarnings("unchecked")
    private <T> T createProxyWithLogging(T target) {
        Class<?>[] interfaces = target.getClass().getInterfaces();

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // Логика "До" выполнения оригинального метода
                System.out.println("[JDK Proxy LOG] До вызова метода: " + method.getName());
                if (args != null) {
                    System.out.println("[JDK Proxy LOG] Аргументы: " + Arrays.toString(args));
                }

                long startTime = System.currentTimeMillis();

                // Вызов оригинального метода
                Object result = method.invoke(target, args);

                long endTime = System.currentTimeMillis();

                // Логика "После" выполнения
                System.out.println("[JDK Proxy LOG] После вызова метода: " + method.getName() + ", время выполнения: " + (endTime - startTime) + "мс");

                return result;
            }
        };

        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                interfaces,
                handler
        );
    }
}
