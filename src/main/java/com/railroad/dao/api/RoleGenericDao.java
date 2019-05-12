package com.railroad.dao.api;

import com.railroad.entity.RoleEntity;

/**
 * DAO for the {@link RoleEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface RoleGenericDao extends GenericDao<RoleEntity, Long> {

    /**
     * Returns role entity from db by name
     *
     * @param name
     * @return RoleEntity with selected name
     */
    RoleEntity findByName(String name);
}
