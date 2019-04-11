package com.railroad.dao.impl;


import com.railroad.dao.api.RoleGenericDao;
import com.railroad.model.RoleEntity;
import org.hibernate.Session;
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

    @PersistenceContext
    private EntityManager entityManager;

    public RoleGenericDaoImpl() {
        super(RoleEntity.class);
    }


    /**
     * *Method for finding RoleEntity in DB
     *
     * @param name
     * @return RoleEntity with selected name
     */
    @Override
    public RoleEntity findByName(String name) {
        RoleEntity roleEntity = (RoleEntity)entityManager.createQuery("select r from RoleEntity r where r.name=:name").
                setParameter("name", name).getSingleResult();
        return roleEntity;
    }
}
