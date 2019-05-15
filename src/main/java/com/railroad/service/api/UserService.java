package com.railroad.service.api;

import com.railroad.dto.user.UserDto;
import com.railroad.entity.UserEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;

/**
 * @author Stanislav Popovich
 */
public interface UserService {

    void save(UserDto userDto) throws RailroadDaoException;

    List<UserDto> getAll() throws RailroadDaoException;

    boolean isAlreadyExist(String userName) throws RailroadDaoException;

    void update(String userName, String role) throws RailroadDaoException;

    void delete(String userName) throws RailroadDaoException;

    UserEntity getCurrentUser() throws RailroadDaoException;
}
