package com.railroad.mapper;

import com.railroad.dto.RoleDto;
import com.railroad.model.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleEntityDtoMapper {

    RoleEntity roleDtoToRoleEntity(RoleDto roleDto);
    RoleDto roleEntityToRoleDto(RoleEntity roleEntity);
    List<RoleDto> roleEntitiesToRoleDtos(List<RoleEntity> roleEntities);
}
