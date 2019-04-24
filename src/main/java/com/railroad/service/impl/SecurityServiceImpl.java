package com.railroad.service.impl;

import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.SecurityService;
import com.railroad.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger logger = Logger.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    public void autoLogin(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if(authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    public void registration(UserDto userDto){
       userService.save(userDto);
       autoLogin(userDto.getUserName(), userDto.getConfirmPassword());
    }

    @Override
    public boolean isAlreadyExist(String userName) {
        return userService.isAlreadyExist(userName);
    }
}
