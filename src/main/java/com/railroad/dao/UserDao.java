package com.railroad.dao;

import com.railroad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDao extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}

