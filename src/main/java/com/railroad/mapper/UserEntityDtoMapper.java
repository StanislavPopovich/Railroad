package com.railroad.mapper;

import com.railroad.dto.RoleDto;
import com.railroad.dto.UserDto;
import com.railroad.model.RoleEntity;
import com.railroad.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserEntityDtoMapper {

    @Mapping(source = "roles", target = "roleEntities")
    UserEntity userDtoToUserEntity(UserDto userDto);
    @Mapping(source = "roleEntities", target = "roles")
    UserDto userEntityToUserDto(UserEntity userEntity);

    default Set<RoleEntity> roleNamesToRoleEntities(Set<String> roles){
        Set<RoleEntity> roleEntities = new HashSet<>();
        for(String name: roles){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(name);
            roleEntities.add(roleEntity);
        }
        return roleEntities;
    }

    default Set<String> roleEntitiesToRoleNames(Set<RoleEntity> roleEntities){
        Set<String> roles = new HashSet<>();
        for(RoleEntity roleEntity: roleEntities){
            roles.add(roleEntity.getName());
        }
        return roles;
    }

    List<UserDto> userEntitiesToUserDtos(List<UserEntity> userEntities);
    List<UserEntity> userDtosToUserEntities(List<UserDto> userDtos);
}
