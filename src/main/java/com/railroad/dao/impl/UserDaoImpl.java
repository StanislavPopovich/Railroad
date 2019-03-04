package com.railroad.dao.impl;

import com.railroad.dao.UserDao;
import com.railroad.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl extends BaseDao<User, Long> implements UserDao {

    public UserDaoImpl() {
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
