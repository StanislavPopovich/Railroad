package com.railroad.dao.api;

import com.railroad.entity.PassengerEntity;
import com.railroad.exceptions.RailroadDaoException;

/**
 * DAO for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface PassengerGenericDao extends GenericDao<PassengerEntity, Long> {

    PassengerEntity findPassengerByAllFields(PassengerEntity passengerEntity) throws RailroadDaoException;

    Long getCountPassengerByNameAndBirthDate(PassengerEntity passengerEntity) throws RailroadDaoException;

}
