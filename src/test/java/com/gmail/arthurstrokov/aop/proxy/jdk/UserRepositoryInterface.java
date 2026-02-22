package com.gmail.arthurstrokov.aop.proxy.jdk;

/**
 * Интерфейс для демонстрации JDK Dynamic Proxy.
 * <p>
 * JDK Dynamic Proxy — это встроенный механизм Java для создания прокси-объектов.
 * Основное ограничение: работает только для интерфейсов.
 * Если класс не реализует ни одного интерфейса, JDK Proxy использовать нельзя (в этом случае применяется CGLib).
 */
public interface UserRepositoryInterface {

    /**
     * Сохранение объекта в хранилище.
     *
     * @param object объект для сохранения.
     */
    void save(Object object);

    /**
     * Получение объекта по идентификатору.
     *
     * @param id идентификатор объекта.
     * @return найденный объект или null.
     */
    Object get(int id);

    /**
     * Удаление объекта по идентификатору.
     *
     * @param id идентификатор объекта для удаления.
     */
    void delete(int id);
}
