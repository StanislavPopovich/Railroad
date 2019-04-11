package com.railroad.dao.impl;

import com.railroad.dao.api.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
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


    /**
     *Method for adding Entity in DB
     *
     * @param entity
     */
    @Override
    public void save(T entity) {
        entityManager.persist(entity);

    }

    /**
     * Method for removing Entity from DB
     *
     * @param entity
     */
    @Override
    public void remove(T entity) {
        entityManager.remove(entity);

    }

    /**
     * Method for getting user by id
     *
     * @param id id
     * @return Entity with selected id
     */
    @Override
    public T getById(ID id) {
        T entity = entityManager.find(clazz, id);
        return entity;
    }

    /**
     * Method for getting all entities from DB
     *
     * @return full list of entities from DB
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        List<T> allEntities = entityManager.createQuery("select e from " + clazz.getName() + " e").getResultList();
       return allEntities;
    }

    /**
     * Method for updating Entity in DB
     *
     * @param entity
     */
    @Override
    public void update(T entity) {

        entityManager.merge(entity);
    }

}
