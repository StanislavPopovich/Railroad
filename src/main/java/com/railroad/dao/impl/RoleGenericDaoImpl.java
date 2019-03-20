package com.railroad.dao.impl;


import com.railroad.dao.api.RoleGenericDao;
import com.railroad.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


/**
 * DAO implementation for the {@link com.railroad.model.Role} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class RoleGenericDaoImpl extends BaseGenericDao<Role, Long> implements RoleGenericDao {

    public RoleGenericDaoImpl() {
        super(Role.class);
    }

    /**
     * *Method for finding Role in DB
     *
     * @param name
     * @return Role with selected name
     */
    @Override
    public Role findByName(String name) {
        Session session = getSession();
        Role role = (Role)session.createQuery("from Role p where p.name=:name").
                setParameter("name", name).uniqueResult();
        return role;
    }
}
