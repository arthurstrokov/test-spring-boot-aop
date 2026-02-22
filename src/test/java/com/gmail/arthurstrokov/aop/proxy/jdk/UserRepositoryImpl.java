package com.gmail.arthurstrokov.aop.proxy.jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Простая реализация репозитория для демонстрации проксирования.
 * JDK Dynamic Proxy будет перехватывать вызовы методов этого класса через интерфейс.
 */
public class UserRepositoryImpl implements UserRepositoryInterface {

    private final List<Object> storage = new ArrayList<>();

    @Override
    public void save(Object object) {
        System.out.println("UserRepositoryImpl: Сохранение объекта: " + object);
        storage.add(object);
    }

    @Override
    public Object get(int id) {
        System.out.println("UserRepositoryImpl: Получение объекта с id: " + id);
        if (id >= 0 && id < storage.size()) {
            return storage.get(id);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        System.out.println("UserRepositoryImpl: Удаление объекта с id: " + id);
        if (id >= 0 && id < storage.size()) {
            storage.remove(id);
        }
    }
}
