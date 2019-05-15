package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dto.role.RoleDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.RoleEntityDtoMapper;
import com.railroad.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleGenericDao roleGenericDao;

    @Autowired
    private RoleEntityDtoMapper roleDtoMapper;

    /**
     * Getting list data transfer objects of all roles
     * @return List of RoleDto
     */
    @Override
    @Transactional
    public List<RoleDto> getAllRolesDto() throws RailroadDaoException {
        List<RoleDto> roles = new ArrayList<>();
        for(RoleDto roleDto: roleDtoMapper.roleEntitiesToRoleDtos(roleGenericDao.getAll())){
            String[] roleArr = roleDto.getName().split("_");
            roleDto.setName(roleArr[1]);
            roles.add(roleDto);
        }
        return roles;
    }
}
