package com.railroad.service.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.model.PassengerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassengerServiceImpl {

    @Autowired
    private PassengerGenericDao passengerGenericDao;



    @Transactional
    //+
    public void savePassenger(PassengerEntity passengerEntity){
        passengerGenericDao.save(passengerEntity);
    }

    //+
    public boolean isAlreadyExist(PassengerEntity passengerEntity){
        if(passengerGenericDao.getCountPassengerByNameAndBirthDate(passengerEntity) > 0){
            return true;
        }
        return false;
    }

    //+
    public PassengerEntity findPassengerByNameAndBirthDate(PassengerEntity passengerEntity){
        return passengerGenericDao.findPassengerByAllFields(passengerEntity);
    }
}
