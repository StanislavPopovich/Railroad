package com.railroad.service.api;

import com.railroad.dto.role.RoleDto;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;

/**
 * @author Stanislav Popovich
 */
public interface RoleService {

    List<RoleDto> getAllRolesDto() throws RailroadDaoException;
}
