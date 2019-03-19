package com.railroad.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String userName;
    private String password;
    private String confirmPassword;
    Set<String> roles;
}
