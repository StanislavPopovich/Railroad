package com.railroad.service.impl;

import com.railroad.dao.impl.RoleDaoImpl;
import com.railroad.dao.impl.UserDaoImpl;
import com.railroad.model.Role;
import com.railroad.model.User;
import com.railroad.service.UserService;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findById(1L, Role.class));
        user.setRoles(roles);
        userDao.persist(user);
    }

    public User findByUsername(String userName) {
        logger.info("in finByUserName method");
        return userDao.findByUserName(userName);
    }

    public List<User> getAllUsers() {
        userDao.setClazz(User.class);
        return userDao.getAll();
    }
}
