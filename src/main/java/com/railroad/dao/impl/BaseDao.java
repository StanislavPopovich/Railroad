package com.railroad.dao.impl;

import com.railroad.dao.Dao;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
public class BaseDao<T> implements Dao<T, Long> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void persist(T entity) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(T entity) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();

    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Long id, Class<?> persistClass) {
        Session session = this.sessionFactory.openSession();
        T entity = (T)session.get(persistClass, id);
        session.close();
        return entity;

    }
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        Session session = this.sessionFactory.openSession();
        List<T> allEntities = session.createQuery("from " + clazz.getName()).list();
        session.close();
        return allEntities;
    }
}
