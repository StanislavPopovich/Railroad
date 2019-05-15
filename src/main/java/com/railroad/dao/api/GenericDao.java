package com.railroad.dao.api;

import com.railroad.exceptions.RailroadDaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Basic Data Access Object interface.
 * Provides CRUD operations
 *
 * @author Stanislav Popovich
 */
public interface GenericDao<T, ID extends Serializable> {

    void save(T entity) throws RailroadDaoException;

    void remove(T entity) throws RailroadDaoException;

    T getById(ID id) throws RailroadDaoException;

    List<T> getAll() throws RailroadDaoException;

    void update(T entity) throws RailroadDaoException;
}
