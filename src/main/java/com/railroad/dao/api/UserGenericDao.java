package com.railroad.dao.api;

import com.railroad.model.UserEntity;

/**
 * DAO for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface UserGenericDao extends GenericDao<UserEntity, Long> {

    /**
     *Method for finding UserEntity in DB
     *
     * @param userName
     * @return UserEntity with selected name
     */
    UserEntity findByUserName(String userName);

    Long getCountUserBuUserName(String userName);

}

