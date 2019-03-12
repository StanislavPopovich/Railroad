package com.railroad.service.api;

import com.railroad.dto.UserDto;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String userName, String password);
    void registration(UserDto userDto);
    boolean isAlreadyExist(String userName);

}
