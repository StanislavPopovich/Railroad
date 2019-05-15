package com.railroad.dao.impl;

import com.railroad.dao.api.GenericDao;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;

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

    private static final org.apache.log4j.Logger logger = Logger.getLogger(BaseGenericDao.class);

    private Class<T> clazz;

    public BaseGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Saves entity to db
     *
     * @param entity
     */
    @Override
    public void save(T entity) throws RailroadDaoException {
        try{
            entityManager.persist(entity);
        }catch (Exception e){
            logger.warn("Exception in BaseGenericDao - save().");
            throw new RailroadDaoException(e);
        }
    }

    /**
     * Removes entity in db
     *
     * @param entity
     */
    @Override
    public void remove(T entity) throws RailroadDaoException {
        try{
            entityManager.remove(entity);
        }catch (Exception e){
            logger.warn("Exception in BaseGenericDao - remove().");
            throw new RailroadDaoException(e);
        }
    }

    /**
     * Returns entity by id
     *
     * @param id id
     * @return Entity with selected id
     */
    @Override
    public T getById(ID id) throws RailroadDaoException {
        T entity;
        try{
            entity = entityManager.find(clazz, id);
        }catch (Exception e){
            logger.warn("Exception in BaseGenericDao - getById().");
            throw new RailroadDaoException(e);
        }
        return entity;
    }

    /**
     * Returns all entities from db
     *
     * @return full list of entities from DB
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() throws RailroadDaoException {
        List<T> allEntities;
        try{
            allEntities = entityManager.createQuery("select e from " + clazz.getName() + " e").getResultList();
        }catch (Exception e){
            logger.warn("Exception in BaseGenericDao - getAll().");
            throw new RailroadDaoException(e);
        }

       return allEntities;
    }

    /**
     * Updates entity to db
     *
     * @param entity
     */
    @Override
    public void update(T entity) throws RailroadDaoException {
        entityManager.merge(entity);
        try{
            entityManager.merge(entity);
        }catch (Exception e){
            logger.warn("Exception in BaseGenericDao - update().");
            throw new RailroadDaoException(e);
        }
    }

    /**
     * Getting current date
     * @return Date
     */
    public Date getCurrentDate(){
        return new Date();
    }

}
