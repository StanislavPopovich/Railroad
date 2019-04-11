package com.railroad.dao.api;

import com.railroad.model.PassengerEntity;

import java.util.Date;

/**
 * DAO for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface PassengerGenericDao extends GenericDao<PassengerEntity, Long> {

    PassengerEntity findPassengerByLastNameAndBirthDate(String lastName, String name, Date date);
    Long getCountPassengerByNameAndBirthDate(PassengerEntity passengerEntity);
}
