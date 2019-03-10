package com.railroad.service.impl;

import com.railroad.dao.impl.RoleGenericDaoImpl;
import com.railroad.dao.impl.UserGenericDaoImpl;
import com.railroad.dto.UserDto;
import com.railroad.mapper.UserDtoMapper;
import com.railroad.model.Role;
import com.railroad.model.User;
import com.railroad.service.api.UserService;
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
    private UserDtoMapper userDtoMapper;

    @Autowired
    private UserGenericDaoImpl userDao;

    @Autowired
    private RoleGenericDaoImpl roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = userDtoMapper.UserDtoToUser(userDto);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    public User findByUsername(String userName) {
        return userDao.findByUserName(userName);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean isAlreadyExist(String userName) {
        User user = userDao.findByUserName(userName);
        if(user != null){
            return true;
        }
        return false;
    }


}
