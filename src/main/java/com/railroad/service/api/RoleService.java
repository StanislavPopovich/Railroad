package com.railroad.service.api;

import com.railroad.dto.RoleDto;

import java.util.List;

/**
 * Service interface for {@link com.railroad.model.RoleEntity}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface RoleService {

    /**
     * The method returns all roleDtos from dao layer
     * @return list of RoleDto
     */
    List<RoleDto> getAll();
}
