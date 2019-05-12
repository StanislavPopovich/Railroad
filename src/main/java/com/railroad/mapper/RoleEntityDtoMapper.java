package com.railroad.mapper;

import com.railroad.dto.role.RoleDto;
import com.railroad.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleEntityDtoMapper {
    RoleDto roleEntityToRoleDto(RoleEntity roleEntity);
    List<RoleDto> roleEntitiesToRoleDtos(List<RoleEntity> roleEntities);
}
