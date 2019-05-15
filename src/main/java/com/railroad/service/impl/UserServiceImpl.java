package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dao.api.UserGenericDao;
import com.railroad.dto.user.UserDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.UserEntityDtoMapper;
import com.railroad.entity.RoleEntity;
import com.railroad.entity.UserEntity;
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
 * @author Stanislav Popovich
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityDtoMapper userDtoMapper;

    @Autowired
    private UserGenericDao userDao;

    @Autowired
    private RoleGenericDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Saving a new entity to database
     * @param userDto user data transfer object
     */
    @Override
    @Transactional
    public void save(UserDto userDto) throws RailroadDaoException {
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

    /**
     * Getting set of roles with Id for saving user
     * @param roleEntities set of roles with only names
     * @return set of roles with set ids
     */
    private Set<RoleEntity> setIdToRoles(Set<RoleEntity> roleEntities) throws RailroadDaoException {
        Set<RoleEntity> roles = roleEntities;
        for(RoleEntity roleEntity: roles){
            roleEntity.setId(roleDao.findByName(roleEntity.getName()).getId());
        }
        return roles;
    }

    /**
     * Getting list data transfer objects of all users
     * @return List of UserDto
     */
    @Transactional
    public List<UserDto> getAll() throws RailroadDaoException {
        return userDtoMapper.userEntitiesToUserDtos(userDao.getAll());
    }

    /**
     * Checking exist user in database by name
     * @param userName user's name
     * @return true or false
     */
    @Override
    public boolean isAlreadyExist(String userName) throws RailroadDaoException {
        if(userDao.getCountUserBuUserName(userName) > 0){
            return true;
        }
        return false;
    }

    /**
     * Changing user's role
     * @param userName user's name
     * @param newRole role's name
     */
    @Override
    @Transactional
    public void update(String userName, String newRole) throws RailroadDaoException {
        UserEntity user = userDao.findByUserName(userName);
        RoleEntity role = roleDao.findByName("ROLE_" + newRole);
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);
        user.setRoleEntities(roles);
        userDao.update(user);
    }

    /**
     * Delete user from database by name
     * @param userName user's name
     */
    @Override
    @Transactional
    public void delete(String userName) throws RailroadDaoException {
        UserEntity userEntity = userDao.findByUserName(userName);
        userDao.remove(userEntity);
    }

    /**
     * Getting current user from session
     * @return user entity
     */
    @Override
    public UserEntity getCurrentUser() throws RailroadDaoException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByUserName(authentication.getName());
    }
}
