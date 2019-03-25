package com.railroad.dao.impl;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.model.UserEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class UserGenericDaoImpl extends BaseGenericDao<UserEntity, Long> implements UserGenericDao {

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
        Session session = getSession();
        UserEntity userEntity = (UserEntity)session.createQuery("select u from UserEntity u where u.userName=:userName").
                setParameter("userName", userName).uniqueResult();
        return userEntity;
    }

}
