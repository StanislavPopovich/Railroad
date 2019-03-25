package com.railroad.dao.impl;


import com.railroad.dao.api.RoleGenericDao;
import com.railroad.model.RoleEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


/**
 * DAO implementation for the {@link RoleEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class RoleGenericDaoImpl extends BaseGenericDao<RoleEntity, Long> implements RoleGenericDao {

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
        Session session = getSession();
        RoleEntity roleEntity = (RoleEntity)session.createQuery("select r from RoleEntity r where r.name=:name").
                setParameter("name", name).uniqueResult();
        return roleEntity;
    }
}
