package com.railroad.dao.impl;


import com.railroad.dao.api.RoleGenericDao;
import com.railroad.entity.RoleEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * DAO implementation for the {@link RoleEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class RoleGenericDaoImpl extends BaseGenericDao<RoleEntity, Long> implements RoleGenericDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(RoleGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public RoleGenericDaoImpl() {
        super(RoleEntity.class);
    }

    /**
     * Returns role entity from db by name
     *
     * @param name role's name
     * @return RoleEntity with selected name
     */
    @Override
    public RoleEntity findByName(String name) throws RailroadDaoException {
        RoleEntity roleEntity;
        try{
            roleEntity = (RoleEntity)entityManager.createQuery("select r from RoleEntity r where r.name=:name").
                    setParameter("name", name).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in RoleGenericDaoImpl - findByName().");
            throw new RailroadDaoException(e);
        }
        return roleEntity;
    }
}
