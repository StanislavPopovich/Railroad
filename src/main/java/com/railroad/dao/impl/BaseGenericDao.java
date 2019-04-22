package com.railroad.dao.impl;

import com.railroad.dao.api.GenericDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Basic DAO implementation.
 *
 * @author Stanislav Popovich
 */
public class BaseGenericDao<T, ID extends Serializable> implements GenericDao<T, ID> {

    private Class<T> clazz;

    public BaseGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(T entity) {
        entityManager.persist(entity);

    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);

    }

    @Override
    public T getById(ID id) {
        T entity = entityManager.find(clazz, id);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        List<T> allEntities = entityManager.createQuery("select e from " + clazz.getName() + " e").getResultList();
       return allEntities;
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    public Date getCurrentDate(){
        return new Date();
    }

}
