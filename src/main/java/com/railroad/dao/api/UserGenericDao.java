package com.railroad.dao.api;

import com.railroad.model.User;

/**
 * DAO for the {@link com.railroad.model.User} objects.
 *
 * @author Stanislav Popovich
 */
public interface UserGenericDao extends GenericDao<User, Long> {

    /**
     *Method for finding User in DB
     *
     * @param userName
     * @return User with selected name
     */
    User findByUserName(String userName);

}

