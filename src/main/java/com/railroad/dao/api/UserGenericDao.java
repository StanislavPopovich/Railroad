package com.railroad.dao.api;

import com.railroad.entity.UserEntity;
import com.railroad.exceptions.RailroadDaoException;


/**
 * DAO for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface UserGenericDao extends GenericDao<UserEntity, Long> {

    UserEntity findByUserName(String userName) throws RailroadDaoException;

    Long getCountUserBuUserName(String userName) throws RailroadDaoException;


}

