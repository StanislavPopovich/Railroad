package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dto.RoleDto;
import com.railroad.mapper.RoleEntityDtoMapper;
import com.railroad.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleGenericDao roleGenericDao;

    @Autowired
    private RoleEntityDtoMapper roleDtoMapper;

    @Override
    //+
    public List<RoleDto> getAll() {
        return roleDtoMapper.roleEntitiesToRoleDtos(roleGenericDao.getAll());
    }

    @Override
    @Transactional
    public List<String> getRolesNames() {
        List<String> roles = new ArrayList<>();
        for(RoleDto roleDto: getAll()){
            String[] roleArr = roleDto.getName().split("_");
            roles.add(roleArr[1]);
        }
        return roles;
    }
}
