package com.railroad.dao.api;

import com.railroad.model.Role;

public interface RoleGenericDao extends GenericDao<Role, Long> {
    Role findByName(String name);
}
