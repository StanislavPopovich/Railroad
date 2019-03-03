package com.railroad.dao;

import com.railroad.model.User;

public interface UserDao extends Dao<User, Long>{

    User findByUserName(String userName);
    User getById(Long id);

}

