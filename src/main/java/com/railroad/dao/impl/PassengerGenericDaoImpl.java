package com.railroad.dao.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.model.PassengerEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class PassengerGenericDaoImpl extends BaseGenericDao<PassengerEntity, Long> implements PassengerGenericDao {

    public PassengerGenericDaoImpl() {
        super(PassengerEntity.class);
    }

    @Override
    public PassengerEntity findPassengerByLastnameAndName(String lastName, String name) {
        Session session = getSession();
        PassengerEntity passengerEntity = (PassengerEntity)session.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName and p.name= :name").setParameter("lastName", lastName).
                setParameter("name", name).uniqueResult();
        return passengerEntity;
    }
}
