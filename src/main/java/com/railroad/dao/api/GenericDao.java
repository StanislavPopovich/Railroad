package com.railroad.dao.api;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    void save(T entity);
    void remove(T entity);
    T getById(ID id);
    List<T> getAll();
}
