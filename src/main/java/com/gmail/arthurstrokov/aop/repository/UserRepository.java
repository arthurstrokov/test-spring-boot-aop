package com.gmail.arthurstrokov.aop.repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository {

    private final List<Object> objectList = new ArrayList<>();

    @Override
    public void save(Object o) {
        objectList.add(o);
    }

    @Override
    public void get(int i) {
        objectList.get(i);
    }
}
