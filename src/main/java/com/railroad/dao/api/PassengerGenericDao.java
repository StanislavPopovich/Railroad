package com.railroad.dao.api;

import com.railroad.entity.PassengerEntity;
import java.util.Date;
import java.util.List;

/**
 * DAO for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface PassengerGenericDao extends GenericDao<PassengerEntity, Long> {

    /**
     * Returns an entity that matches by last name, first name, and date of birth
     * @param passengerEntity
     * @return entity from db
     */
    PassengerEntity findPassengerByAllFields(PassengerEntity passengerEntity);

    /**
     * Returns count of entities that matches by last name, first name, and date of birth
     * @param passengerEntity
     * @return 0 or 1
     */
    Long getCountPassengerByNameAndBirthDate(PassengerEntity passengerEntity);

    /**
     * Returns entities that matches by name
     * @param name
     * @return list of entities
     */
    List<PassengerEntity> findPassengersByName(String name);

    /**
     * Returns entities that matches by last name
     * @param lastName
     * @return list of entities
     */
    List<PassengerEntity> findPassengersByLastName(String lastName);

    /**
     * Returns entities that matches by birth date
     * @param birthDate
     * @return list of entities
     */
    List<PassengerEntity> findPassengersByBirthDate(Date birthDate);

    /**
     * Returns entities that matches by last name and name
     * @param lastName
     * @param name
     * @return list of entities
     */
    List<PassengerEntity> findPassengersByLastNameAndName(String lastName, String name);

    /**
     * Returns entities that matches by last name and birth date
     * @param lastName
     * @param birthDate
     * @return list of entities
     */
    List<PassengerEntity> findPassengersByLastNameAndBirthDate(String lastName, Date birthDate);

    /**
     * Returns entities that matches by name and birth date
     * @param name
     * @param birthDate
     * @return
     */
    List<PassengerEntity> findPassengersByNameAndBirthDate(String name, Date birthDate);
}
