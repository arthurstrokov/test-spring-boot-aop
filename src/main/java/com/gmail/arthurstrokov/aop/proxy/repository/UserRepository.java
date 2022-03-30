package com.gmail.arthurstrokov.aop.proxy.repository;

import com.gmail.arthurstrokov.aop.proxy.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository {

    private final List<Object> objectList = new ArrayList<>();

    @Override
    public Object save(Object o) {
        return objectList.add(o);
    }

    @Override
    public Object get(int i) {
        return objectList.get(i);
    }
}
