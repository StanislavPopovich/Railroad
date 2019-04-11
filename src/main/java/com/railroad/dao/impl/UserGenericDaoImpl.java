package com.railroad.dao.impl;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.model.UserEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO implementation for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class UserGenericDaoImpl extends BaseGenericDao<UserEntity, Long> implements UserGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserGenericDaoImpl() {
        super(UserEntity.class);
    }

    /**
     *Method for finding UserEntity in DB
     *
     * @param userName
     * @return UserEntity with selected name
     */
    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity userEntity = (UserEntity)entityManager.createQuery("select u from UserEntity u where u.userName=:userName").
                setParameter("userName", userName).getSingleResult();
        return userEntity;
    }

    @Override
    public Long getCountUserBuUserName(String userName) {
        Long count = (Long) entityManager.createQuery("select count(u) from " +
                "UserEntity u where u.userName= :userName").setParameter("userName", userName).getSingleResult();
        return count;
    }

}
