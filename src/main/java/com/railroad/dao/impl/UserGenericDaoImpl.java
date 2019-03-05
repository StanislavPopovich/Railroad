package com.railroad.dao.impl;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class UserGenericDaoImpl extends BaseGenericDao<User, Long> implements UserGenericDao {

    public UserGenericDaoImpl() {
        super(User.class);
    }


    @Override
    public User findByUserName(String userName) {
        Session session = getSession();
        User user = (User)session.createQuery("from User p where p.userName=:userName").
                setParameter("userName", userName).uniqueResult();
        if(user != null){
            Hibernate.initialize(user.getRoles());
        }
        session.close();
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = getById(id);
        if(user != null){
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }
}
