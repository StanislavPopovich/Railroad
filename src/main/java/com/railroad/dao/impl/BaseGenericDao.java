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

public class BaseGenericDao<T, ID extends Serializable> implements GenericDao<T, ID> {

    private Class<T> clazz;

    public BaseGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(T entity) {
        Session session = getSession();
        try{
            session.persist(entity);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void remove(T entity) {
        Session session = getSession();
        session.remove(entity);

    }

    @Override
    public T getById(ID id) {
        Session session = getSession();
        T entity = session.get(clazz, id);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        Session session = getSession();
        List<T> allEntities = session.createQuery("from " + clazz.getName()).list();
        return allEntities;
    }

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
