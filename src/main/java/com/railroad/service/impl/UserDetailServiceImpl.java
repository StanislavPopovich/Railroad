package com.railroad.service.impl;


import com.railroad.dao.UserDao;
import com.railroad.model.Role;
import com.railroad.model.User;
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
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("in loadUserByUsername method");
        User user = userDao.findByUserName(userName);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role: user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), grantedAuthorities);
    }
}
