package com.railroad.dao.impl;


import com.railroad.dao.api.RoleGenericDao;
import com.railroad.model.Role;
import org.springframework.stereotype.Repository;



@Repository
public class RoleGenericDaoImpl extends BaseGenericDao<Role, Long> implements RoleGenericDao {

    public RoleGenericDaoImpl() {
        super(Role.class);
    }
}
