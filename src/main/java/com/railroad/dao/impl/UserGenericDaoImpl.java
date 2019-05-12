package com.railroad.dao.impl;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.entity.RoleEntity;
import com.railroad.entity.UserEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO implementation for the {@link UserEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class UserGenericDaoImpl extends BaseGenericDao<UserEntity, Long> implements UserGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserGenericDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity userEntity = (UserEntity)entityManager.createQuery("select u from UserEntity u where u.userName=:userName").
                setParameter("userName", userName).getSingleResult();
        return userEntity;
    }

    @Override
    public Long getCountUserBuUserName(String userName) {
        Long count = (Long) entityManager.createQuery("select count(u) from " +
                "UserEntity u where u.userName= :userName").setParameter("userName", userName).getSingleResult();
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> getUsersByRole(RoleEntity roleEntity) {
        List<UserEntity> userEntities = entityManager.createQuery("select u from UserEntity u where " +
                "u.roleEntities= :roleEntity").setParameter("roleEntity", roleEntity).getResultList();
        return userEntities;
    }

}
