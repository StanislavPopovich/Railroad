package com.railroad.service.impl;


import com.railroad.dao.api.UserGenericDao;
import com.railroad.entity.RoleEntity;
import com.railroad.entity.UserEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stanislav Popovich
 */

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserGenericDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = null;
        try {
            userEntity = userDao.findByUserName(userName);
        } catch (RailroadDaoException e) {
            e.printStackTrace();
        }
        if(userEntity != null){
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for(RoleEntity roleEntity : userEntity.getRoleEntities()){
                grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
            }
            return new org.springframework.security.core.userdetails.User(userEntity.getUserName(),
                    userEntity.getPassword(), grantedAuthorities);
        }
        return null;

    }
}
