package com.railroad.mapper;

import com.railroad.dto.UserDto;
import com.railroad.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "confirmPassword", target = "confirmPassword")
    User userDtoToUser(UserDto userDto);

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "confirmPassword", target = "confirmPassword")
    UserDto userToUserDto(User user);
}
