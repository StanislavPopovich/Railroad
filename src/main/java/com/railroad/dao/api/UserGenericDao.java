package com.railroad.dao.api;

import com.railroad.entity.RoleEntity;
import com.railroad.entity.UserEntity;

import java.util.List;

/**
 * DAO for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface UserGenericDao extends GenericDao<UserEntity, Long> {

    /**
     *Returns entity from db by user name
     *
     * @param userName
     * @return UserEntity with selected name
     */
    UserEntity findByUserName(String userName);

    /**
     * Returns count of users that matches of user name
     * @param userName
     * @return 0 or 1
     */
    Long getCountUserBuUserName(String userName);

    /**
     * Returns entities from db by role
     * @param roleEntity
     * @return list of entities
     */
    List<UserEntity> getUsersByRole(RoleEntity roleEntity);

}

