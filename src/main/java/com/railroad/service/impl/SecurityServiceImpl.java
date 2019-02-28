package com.railroad.service.impl;

import com.railroad.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    public String findLoggedInUsername() {
        return null;
    }

    public void autoLogin(String userName, String password) {

    }
}
