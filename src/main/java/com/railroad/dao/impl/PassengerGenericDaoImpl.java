package com.railroad.dao.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.model.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerGenericDaoImpl extends BaseGenericDao<Passenger, Long> implements PassengerGenericDao {

    public PassengerGenericDaoImpl() {
        super(Passenger.class);
    }

}
