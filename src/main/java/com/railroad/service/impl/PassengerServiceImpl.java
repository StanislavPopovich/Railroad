package com.railroad.service.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.dto.passenger.PassengerUpdateDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.entity.PassengerEntity;
import com.railroad.service.api.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Stanislav Popovich
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerGenericDao passengerGenericDao;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;


    /**
     * Saving passenger to db
     * @param passengerEntity passenger
     */
    @Transactional
    @Override
    public void save(PassengerEntity passengerEntity) throws RailroadDaoException {
        passengerGenericDao.save(passengerEntity);
    }

    /**
     * Checking that passenger already exist in db
     * @param passengerEntity
     * @return boolean
     */
    @Override
    public boolean isAlreadyExist(PassengerEntity passengerEntity) throws RailroadDaoException {
        if(passengerGenericDao.getCountPassengerByNameAndBirthDate(passengerEntity) > 0){
            return true;
        }
        return false;
    }

    /**
     * Finding passenger in db
     * @param passengerEntity passenger
     * @return PassengerEntity
     */
    @Override
    public PassengerEntity findPassengerByNameAndBirthDate(PassengerEntity passengerEntity) throws RailroadDaoException {
        return passengerGenericDao.findPassengerByAllFields(passengerEntity);
    }

    /**
     * Updating Passenger data
     * @param passengerUpdateDto
     */
    @Override
    public void update(PassengerUpdateDto passengerUpdateDto) throws RailroadDaoException {
        PassengerEntity newPassenger = passengerEntityDtoMapper.passengerUpdateDtoToEntity(passengerUpdateDto);
        PassengerEntity oldPassenger = passengerEntityDtoMapper.passengerUpdateDtoToOldEntity(passengerUpdateDto);
        PassengerEntity passengerEntity = passengerGenericDao.findPassengerByAllFields(oldPassenger);
        newPassenger.setId(passengerEntity.getId());
        passengerGenericDao.update(newPassenger);

    }

}
