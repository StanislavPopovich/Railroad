package com.railroad.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public abstract class BaseController {

    public String getUrl(){
        Collection<GrantedAuthority> authorities = (Collection) SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String url = null;
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN")){
                url = "redirect:/railroad/admin/";
            } else{
                url = "redirect:/railroad/moderator/";
            }
        }
        return url;
    }
}
