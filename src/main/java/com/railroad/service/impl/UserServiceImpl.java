package com.railroad.service.impl;

import com.railroad.dao.RoleDao;
import com.railroad.dao.UserDao;
import com.railroad.model.Role;
import com.railroad.model.User;
import com.railroad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        System.out.println("1");
        roles.add(roleDao.getRoleById(1L));
        System.out.println(2);
        user.setRoles(roles);
        userDao.save(user);

    }

    public User findByUsername(String userName) {
        return userDao.findByUserName(userName);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
