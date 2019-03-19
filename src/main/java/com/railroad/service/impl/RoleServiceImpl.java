package com.railroad.service.impl;

import com.railroad.dao.api.RoleGenericDao;
import com.railroad.dto.RoleDto;
import com.railroad.mapper.api.RoleDtoMapper;
import com.railroad.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleGenericDao roleGenericDao;

    @Autowired
    private RoleDtoMapper roleDtoMapper;

    @Override
    public List<RoleDto> getAll() {
        return roleDtoMapper.rolesToRoleDtos(roleGenericDao.getAll());
    }
}
