package com.railroad.dao.api;

import com.railroad.model.Role;

/**
 * DAO for the {@link com.railroad.model.Role} objects.
 *
 * @author Stanislav Popovich
 */
public interface RoleGenericDao extends GenericDao<Role, Long> {

    /**
     *Method for finding Role in DB
     *
     * @param name
     * @return Role with selected name
     */
    Role findByName(String name);
}
