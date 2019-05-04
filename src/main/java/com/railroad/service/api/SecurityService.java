package com.railroad.service.api;

import com.railroad.dto.user.UserDto;

public interface SecurityService {

    String findLoggedInUsername();

    /**
     * The method
     * @param userName
     * @param password
     */
    void autoLogin(String userName, String password);
    void registration(UserDto userDto);
    boolean isAlreadyExist(String userName);

}
