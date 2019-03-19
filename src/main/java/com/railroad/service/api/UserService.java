package com.railroad.service.api;

import com.railroad.dto.UserDto;
import com.railroad.model.User;
import java.util.List;

/**
 * Service interface for {@link com.railroad.model.User}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface UserService {

    void save(UserDto userDto);
    UserDto findByUsername(String userName);
    List<UserDto> getAll();
    boolean isAlreadyExist(String userName);
    void update(UserDto userDto);
}
