package com.railroad.service.api;

import com.railroad.dto.passenger.PassengerUpdateDto;
import com.railroad.entity.PassengerEntity;
import com.railroad.exceptions.RailroadDaoException;

/**
 * @author Stanislav Popovich
 */

public interface PassengerService  {

    void save(PassengerEntity passengerEntity) throws RailroadDaoException;

    boolean isAlreadyExist(PassengerEntity passengerEntity) throws RailroadDaoException;

    PassengerEntity findPassengerByNameAndBirthDate(PassengerEntity passengerEntity) throws RailroadDaoException;

    void update(PassengerUpdateDto passengerUpdateDto) throws RailroadDaoException;
}
