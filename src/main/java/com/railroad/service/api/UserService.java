package com.railroad.service.api;

import com.railroad.dto.user.UserDto;
import com.railroad.model.UserEntity;
import java.util.List;

/**
 * Service interface for {@link UserEntity}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface UserService {

    /**
     * The method sends userDto to dao layer
     * @param userDto
     */
    void save(UserDto userDto);

    /**
     * The method returns userDto by user name from dao layer
     * @param userName
     * @return UserDto
     */
    UserDto findByUsername(String userName);

    /**
     * The method returns all userDtos from dao layer
     * @return list of UserDto
     */
    List<UserDto> getAll();

    boolean isAlreadyExist(String userName);

    /**
     * The method sends userDto to dao layer for update userEntity
     * @param userDto
     */
    void update(UserDto userDto);

    /**
     * The method sends UserEntity by userName to dao layer for remove
     * @param userName
     */
    void delete(String userName);

    UserEntity getCurrentUser();
}
