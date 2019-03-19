package com.railroad.mapper.api;

import com.railroad.dto.RoleDto;
import com.railroad.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleDtoMapper {
    RoleDto roleToRoleDto(Role role);
    List<RoleDto> rolesToRoleDtos(List<Role> roles);
}
