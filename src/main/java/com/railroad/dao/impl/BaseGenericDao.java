package com.railroad.dao.impl;

import com.railroad.dao.api.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SessionFactory sessionFactory;


    /**
     *Method for adding Entity in DB
     *
     * @param entity
     */
    @Override
    public void save(T entity) {
        Session session = getSession();
        try{
            session.persist(entity);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Method for removing Entity from DB
     *
     * @param entity
     */
    @Override
    public void remove(T entity) {
        Session session = getSession();
        session.remove(entity);

    }

    /**
     * Method for getting user by id
     *
     * @param id id
     * @return Entity with selected id
     */
    @Override
    public T getById(ID id) {
        Session session = getSession();
        T entity = session.get(clazz, id);
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
        Session session = getSession();
        List<T> allEntities = session.createQuery("from " + clazz.getName()).list();
        return allEntities;
    }

    /**
     * Method for updating Entity in DB
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        Session session = getSession();
        session.clear();
        session.update(entity);
    }

    /**
     * Method for getting session
     */
    protected final Session getSession(){
        Session session;
        try{
            session = this.sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            session = sessionFactory.openSession();
        }
        return session;
    }
}
