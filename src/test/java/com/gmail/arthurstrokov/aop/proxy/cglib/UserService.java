package com.gmail.arthurstrokov.aop.proxy.cglib;

/**
 * Класс для демонстрации CGLib Dynamic Proxy.
 * В отличие от JDK Proxy, CGLib работает путем создания подкласса (наследника) целевого класса.
 * Поэтому методы не обязательно должны быть описаны в интерфейсе.
 */
public class UserService {

    /**
     * Пример метода для сохранения пользователя.
     */
    public void saveUser(String name, int age) {
        System.out.println("UserService: Сохранение пользователя " + name + " (возраст: " + age + ")");
        try {
            // Имитация задержки (работа с БД)
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Пример метода для получения данных.
     */
    public Object getUser(int id) {
        System.out.println("UserService: Получение пользователя по id: " + id);
        return "User#" + id;
    }

    /**
     * Пример метода для удаления.
     */
    public void deleteUser(int id) {
        System.out.println("UserService: Удаление пользователя с id: " + id);
    }
}
