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
     * Saves entity to db
     *
     * @param entity
     */
    void save(T entity);

    /**
     * Removes entity in db
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * Returns entity by id
     *
     * @param id id
     * @return Entity with selected id
     */
    T getById(ID id);

    /**
     * Returns all entities from db
     *
     * @return full list of entities from DB
     */
    List<T> getAll();

    /**
     * Updates entity to db
     *
     * @param entity
     */
    void update(T entity);
}
