package com.railroad.dao.api;

import java.io.Serializable;
import java.util.List;

/**
 * Basic Data Access Object interface.
 * Provides CRUD operations
 *
 * @author Stanislav Popovich
 */
public interface GenericDao<T, ID extends Serializable> {
    /**
     * Method for adding Entity in DB
     *
     * @param entity entity
     */
    void save(T entity);

    /**
     * Method for removing Entity from DB
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * Method for getting user by id
     *
     * @param id id
     * @return Entity with selected id
     */
    T getById(ID id);

    /**
     * Method for getting all entities from DB
     *
     * @return full list of entities from DB
     */
    List<T> getAll();

    /**
     * Method for updating Entity in DB
     *
     * @param entity
     */
    void update(T entity);
}
