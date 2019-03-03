package com.railroad.dao.impl;

import com.railroad.dao.UserDao;
import com.railroad.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUserName(String userName) {
        Session session = this.sessionFactory.openSession();
        User user = (User)session.createQuery("from User p where p.userName=:userName").
                setParameter("userName", userName).uniqueResult();
        if(user != null){
            Hibernate.initialize(user.getRoles());
        }
        session.close();
        return user;
    }

    @Override
    public User getById(Long id) {
        User user = findById(id, User.class);
        if(user != null){
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }
}
