package com.railroad.service.impl;

import com.railroad.dao.UserDao;
import com.railroad.model.User;
import com.railroad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for {@link com.railroad.model.User}
 * @author Stanislav Popovich
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {

    }

    public User findByUsername(String userName) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }
}
