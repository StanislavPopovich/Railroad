package com.railroad.dao.api;

import com.railroad.entity.RoleEntity;
import com.railroad.exceptions.RailroadDaoException;

/**
 * DAO for the {@link RoleEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface RoleGenericDao extends GenericDao<RoleEntity, Long> {

    RoleEntity findByName(String name) throws RailroadDaoException;
}
