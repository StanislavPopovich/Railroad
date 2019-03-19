package com.railroad.mapper.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dto.UserDto;
import com.railroad.mapper.api.UserDtoMapper;
import com.railroad.model.Role;
import com.railroad.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    private static final Logger logger = Logger.getLogger(UserDtoMapperImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleGenericDao roleDao;

    @Override
    public User userDtoToUser(UserDto userDto) {
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setUserName(userDto.getUserName());
            Set<Role> roles = new HashSet<>();
            if(userDto.getRoles() == null){
                roles.add(roleDao.findByName("ROLE_USER"));
            }else{
                for(String roleName: userDto.getRoles()){
                    if(roleName.equals("ROLE_MODERATOR") || roleName.equals("ROLE_ADMIN")
                            || roleName.equals("ROLE_USER")){
                        roles.add(roleDao.findByName(roleName));
                    }
                }
            }
            user.setRoles(roles);
            return user;
        }
    }

    @Override
    public UserDto userToUserDto(User user) {
        if(user == null){
            return null;
        }else{
            UserDto userDto = new UserDto();
            userDto.setPassword(user.getPassword());
            userDto.setUserName(user.getUserName());
            Set<String> roles = new HashSet<>();
            for(Role role: user.getRoles()){
                roles.add(role.getName());
            }
            return userDto;
        }
    }

    @Override
    public List<UserDto> usersToUserDtos(List<User> users) {
        if(users.isEmpty()){
            return null;
        }
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: users){
            UserDto userDto = new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setPassword(user.getPassword());
            Set<String> roles = new HashSet<>();
            for(Role role: user.getRoles()){
                roles.add(role.getName());
            }
            userDto.setRoles(roles);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
