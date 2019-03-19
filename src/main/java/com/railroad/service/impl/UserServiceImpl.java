package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dao.api.UserGenericDao;
import com.railroad.dto.UserDto;
import com.railroad.mapper.api.UserDtoMapper;
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
    private UserGenericDao userDao;

    @Autowired
    private RoleGenericDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        userDao.save(user);
    }

    public UserDto findByUsername(String userName) {
        return userDtoMapper.userToUserDto(userDao.findByUserName(userName));
    }

    public List<UserDto> getAll() {
        List<UserDto> userDtos = userDtoMapper.usersToUserDtos(userDao.getAll());
        return userDtos;
    }

    @Override
    public boolean isAlreadyExist(String userName) {
        User user = userDao.findByUserName(userName);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public void update(UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        user.setId(userDao.findByUserName(userDto.getUserName()).getId());
        userDao.update(user);
    }


}
