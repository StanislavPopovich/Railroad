package com.railroad.service.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.dto.passenger.PassengerUpdateDto;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.model.PassengerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassengerService {

    @Autowired
    private PassengerGenericDao passengerGenericDao;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;



    @Transactional
    public void save(PassengerEntity passengerEntity){
        passengerGenericDao.save(passengerEntity);
    }

    public boolean isAlreadyExist(PassengerEntity passengerEntity){
        if(passengerGenericDao.getCountPassengerByNameAndBirthDate(passengerEntity) > 0){
            return true;
        }
        return false;
    }

    public PassengerEntity findPassengerByNameAndBirthDate(PassengerEntity passengerEntity){
        return passengerGenericDao.findPassengerByAllFields(passengerEntity);
    }

    public void update(PassengerUpdateDto passengerUpdateDto){
        PassengerEntity newPassenger = passengerEntityDtoMapper.passengerUpdateDtoToEntity(passengerUpdateDto);
        PassengerEntity oldPassenger = passengerEntityDtoMapper.passengerUpdateDtoToOldEntity(passengerUpdateDto);
        PassengerEntity passengerEntity = passengerGenericDao.findPassengerByAllFields(oldPassenger);
        newPassenger.setId(passengerEntity.getId());
        passengerGenericDao.update(newPassenger);

    }

}
