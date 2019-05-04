package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dao.api.UserGenericDao;
import com.railroad.dto.user.UserDto;
import com.railroad.mapper.UserEntityDtoMapper;
import com.railroad.model.RoleEntity;
import com.railroad.model.UserEntity;
import com.railroad.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class for {@link UserEntity}
 * @author Stanislav Popovich
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    private UserEntityDtoMapper userDtoMapper;

    @Autowired
    private UserGenericDao userDao;

    @Autowired
    private RoleGenericDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void save(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if(userDto.getRoles() == null){
            Set<String> roles = new HashSet<>();
            roles.add("USER");
            userDto.setRoles(roles);
        }
        UserEntity userEntity = userDtoMapper.userDtoToUserEntity(userDto);
        setIdToRoles(userEntity.getRoleEntities());
        userDao.save(userEntity);
    }

    private Set<RoleEntity> setIdToRoles(Set<RoleEntity> roleEntities){
        Set<RoleEntity> roles = roleEntities;
        for(RoleEntity roleEntity: roles){
            roleEntity.setId(roleDao.findByName(roleEntity.getName()).getId());
        }
        return roles;
    }

    @Transactional
    @Override
    public UserDto findByUsername(String userName) {
        return userDtoMapper.userEntityToUserDto(userDao.findByUserName(userName));
    }

    @Transactional
    public List<UserDto> getAll() {
        List<UserDto> userDtos = userDtoMapper.userEntitiesToUserDtos(userDao.getAll());
        return userDtos;
    }

    @Override
    public boolean isAlreadyExist(String userName) {
        if(userDao.getCountUserBuUserName(userName) > 0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void update(UserDto userDto) {
        UserEntity userEntity = userDtoMapper.userDtoToUserEntity(userDto);
        userEntity.setId(userDao.findByUserName(userEntity.getUserName()).getId());
        Set<RoleEntity> roles = setIdToRoles(userEntity.getRoleEntities());
        userEntity.setRoleEntities(roles);
        userDao.update(userEntity);
    }

    @Override
    @Transactional
    public void delete(String userName) {
        UserEntity userEntity = userDao.findByUserName(userName);
        userDao.remove(userEntity);
    }

    @Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByUserName(authentication.getName());
    }
}
