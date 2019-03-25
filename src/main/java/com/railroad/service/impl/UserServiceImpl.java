package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dao.api.UserGenericDao;
import com.railroad.dto.UserDto;
import com.railroad.mapper.UserEntityDtoMapper;
import com.railroad.model.RoleEntity;
import com.railroad.model.UserEntity;
import com.railroad.service.api.RoleService;
import com.railroad.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void save(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if(userDto.getRoles() == null){
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            userDto.setRoles(roles);
        }
        UserEntity userEntity = userDtoMapper.userDtoToUserEntity(userDto);
        setIdToRoles(userEntity.getRoleEntities());
        userDao.save(userEntity);
    }

    private void setIdToRoles(Set<RoleEntity> roleEntities){
        for(RoleEntity roleEntity: roleEntities){
            roleEntity.setId(roleDao.findByName(roleEntity.getName()).getId());
        }
    }

    public UserDto findByUsername(String userName) {
        return userDtoMapper.userEntityToUserDto(userDao.findByUserName(userName));
    }

    public List<UserDto> getAll() {
        List<UserDto> userDtos = userDtoMapper.userEntitiesToUserDtos(userDao.getAll());
        return userDtos;
    }

    @Override
    public boolean isAlreadyExist(String userName) {
        UserEntity userEntity = userDao.findByUserName(userName);
        if(userEntity != null){
            return true;
        }
        return false;
    }

    @Override
    public void update(UserDto userDto) {
        UserEntity userEntity = userDtoMapper.userDtoToUserEntity(userDto);
        userEntity.setId(userDao.findByUserName(userEntity.getUserName()).getId());
        setIdToRoles(userEntity.getRoleEntities());
        Set<RoleEntity> roleEntities1 = userEntity.getRoleEntities();
        for(RoleEntity entity: roleEntities1){
            logger.info(entity.getId());
            logger.info(entity.getName());
        }
        userDao.update(userEntity);
    }


}
