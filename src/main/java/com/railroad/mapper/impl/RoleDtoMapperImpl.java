package com.railroad.mapper.impl;

import com.railroad.dto.RoleDto;
import com.railroad.mapper.api.RoleDtoMapper;
import com.railroad.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDtoMapperImpl implements RoleDtoMapper {

    @Override
    public RoleDto roleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }

    @Override
    public List<RoleDto> rolesToRoleDtos(List<Role> roles) {
        if(roles.isEmpty()){
            return null;
        }
        List<RoleDto> roleDtos = new ArrayList<>();
        for(Role role: roles){
            RoleDto roleDto = new RoleDto();
            roleDto.setName(role.getName());
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }
}
