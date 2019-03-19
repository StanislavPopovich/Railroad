package com.railroad.dao.impl;


import com.railroad.dao.api.RoleGenericDao;
import com.railroad.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;



@Repository
public class RoleGenericDaoImpl extends BaseGenericDao<Role, Long> implements RoleGenericDao {

    public RoleGenericDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        Session session = getSession();
        Role role = (Role)session.createQuery("from Role p where p.name=:name").
                setParameter("name", name).uniqueResult();
        return role;
    }
}
