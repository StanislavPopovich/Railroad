package com.railroad.dao.impl;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.entity.RoleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.UserEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO implementation for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class UserGenericDaoImpl extends BaseGenericDao<UserEntity, Long> implements UserGenericDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public UserGenericDaoImpl() {
        super(UserEntity.class);
    }

    /**
     *Returns entity from db by user name
     *
     * @param userName
     * @return UserEntity with selected name
     */
    @Override
    public UserEntity findByUserName(String userName) throws RailroadDaoException {
        UserEntity userEntity;
        try{
            userEntity = (UserEntity)entityManager.createQuery("select u from UserEntity u where u.userName=:userName").
                    setParameter("userName", userName).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in UserGenericDaoImpl - findByUserName().");
            throw new RailroadDaoException(e);
        }
        return userEntity;
    }

    /**
     * Returns count of users that matches of user name
     * @param userName
     * @return 0 or 1
     */
    @Override
    public Long getCountUserBuUserName(String userName) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(u) from " +
                    "UserEntity u where u.userName= :userName").setParameter("userName", userName).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in UserGenericDaoImpl - getCountUserBuUserName().");
            throw new RailroadDaoException(e);
        }
        return count;
    }


}
