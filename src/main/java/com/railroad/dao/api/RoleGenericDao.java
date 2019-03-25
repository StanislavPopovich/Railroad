package com.railroad.dao.api;

import com.railroad.model.RoleEntity;

/**
 * DAO for the {@link RoleEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface RoleGenericDao extends GenericDao<RoleEntity, Long> {

    /**
     *Method for finding RoleEntity in DB
     *
     * @param name
     * @return RoleEntity with selected name
     */
    RoleEntity findByName(String name);
}
