package com.railroad.dao;

import java.util.List;

public interface Dao<T, ID> {

    void persist(T t);
    void remove(T t);
    T findById(ID id, Class<?> persistClass);
    List<T> getAll();
}
