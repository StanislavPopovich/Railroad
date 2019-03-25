package com.railroad.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {
    private String userName;
    private String password;
    private String confirmPassword;
    private Set<String> roles;
}
