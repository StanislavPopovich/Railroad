package com.railroad.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

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

    public String getStation(String station, List<String> stations){
        StringBuilder strStations = new StringBuilder();
        for(int i = 0; i < stations.size(); i++){
            if(!stations.get(i).equals(station)){
                if(i == stations.size() - 1){
                    strStations.append(stations.get(i));
                }else{
                    strStations.append(stations.get(i) + "/");
                }
            }
        }
        return strStations.toString();
    }


}
