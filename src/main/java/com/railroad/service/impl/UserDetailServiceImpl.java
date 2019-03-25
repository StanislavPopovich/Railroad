package com.railroad.service.impl;


import com.railroad.dao.api.UserGenericDao;
import com.railroad.model.RoleEntity;
import com.railroad.model.UserEntity;
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

public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserGenericDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByUserName(userName);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(RoleEntity roleEntity : userEntity.getRoleEntities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
        }

        return new org.springframework.security.core.userdetails.User(userEntity.getUserName(),
                userEntity.getPassword(), grantedAuthorities);
    }
}
