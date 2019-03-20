package com.railroad.dao.impl;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link com.railroad.model.User} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class UserGenericDaoImpl extends BaseGenericDao<User, Long> implements UserGenericDao {

    public UserGenericDaoImpl() {
        super(User.class);
    }

    /**
     *Method for finding User in DB
     *
     * @param userName
     * @return User with selected name
     */
    @Override
    public User findByUserName(String userName) {
        Session session = getSession();
        User user = (User)session.createQuery("from User p where p.userName=:userName").
                setParameter("userName", userName).uniqueResult();
        return user;
    }

}
