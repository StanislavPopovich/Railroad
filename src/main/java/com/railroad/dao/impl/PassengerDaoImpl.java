package com.railroad.dao.impl;

import com.railroad.dao.PassengerDao;
import com.railroad.model.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDaoImpl extends BaseDao<Passenger, Long> implements PassengerDao {

    public PassengerDaoImpl() {
        super(Passenger.class);
    }

}
