package com.railroad.mapper;

import com.railroad.dto.user.UserDto;
import com.railroad.entity.RoleEntity;
import com.railroad.entity.UserEntity;
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
            roleEntity.setName("ROLE_" + name);
            roleEntities.add(roleEntity);
        }
        return roleEntities;
    }

    default Set<String> roleEntitiesToRoleNames(Set<RoleEntity> roleEntities){
        Set<String> roles = new HashSet<>();
        for(RoleEntity roleEntity: roleEntities){
            String[] roleArr = roleEntity.getName().split("_");
            roles.add(roleArr[1]);
        }
        return roles;
    }

    List<UserDto> userEntitiesToUserDtos(List<UserEntity> userEntities);
}
