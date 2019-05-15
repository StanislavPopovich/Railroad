package com.railroad.service.api;

import com.railroad.dto.user.UserDto;
import com.railroad.exceptions.RailroadDaoException;

/**
 * @author Stanislav Popovich
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String userName, String password);

    void registration(UserDto userDto) throws RailroadDaoException;

    boolean isAlreadyExist(String userName) throws RailroadDaoException;

}
