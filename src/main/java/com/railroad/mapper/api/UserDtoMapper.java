package com.railroad.mapper.api;

import com.railroad.dto.UserDto;
import com.railroad.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDtoMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> users);
}
