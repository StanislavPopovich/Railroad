package com.railroad.dao.api;

import com.railroad.model.User;

public interface UserGenericDao extends GenericDao<User, Long> {

    User findByUserName(String userName);
    User findById(Long id);

}

