package com.railroad.dao.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.entity.PassengerEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO implementation for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class PassengerGenericDaoImpl extends BaseGenericDao<PassengerEntity, Long> implements PassengerGenericDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(PassengerGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public PassengerGenericDaoImpl() {
        super(PassengerEntity.class);
    }

    /**
     * Finding an entity that matches by last name, first name and date of birth
     * @param passenger
     * @return entity from db
     */

    @Override
    public PassengerEntity findPassengerByAllFields(PassengerEntity passenger) throws RailroadDaoException {
        PassengerEntity passengerEntity;
        try{
            passengerEntity = (PassengerEntity)entityManager.createQuery("select p from PassengerEntity p " +
                    "where p.lastName= :lastName and p.name= :name and p.birthDate= :birthDate").
                    setParameter("lastName", passenger.getLastName()).
                    setParameter("name", passenger.getName()).
                    setParameter("birthDate", passenger.getBirthDate()).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in PassengerGenericDaoImpl - findPassengerByAllFields().");
            throw new RailroadDaoException(e);
        }
        return passengerEntity;
    }

    /**
     * Returns count of entities that matches by last name, first name and date of birth
     * @param passengerEntity
     * @return 0 or 1
     */
    @Override
    public Long getCountPassengerByNameAndBirthDate(PassengerEntity passengerEntity) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(p) from " +
                    "PassengerEntity p where p.lastName= :lastName and p.name= :name " +
                    "and p.birthDate = :birthDate").setParameter("lastName", passengerEntity.getLastName()).
                    setParameter("name", passengerEntity.getName()).
                    setParameter("birthDate", passengerEntity.getBirthDate()).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in BaseGenericDao - getCountPassengerByNameAndBirthDate().");
            throw new RailroadDaoException(e);
        }
        return count;
    }

}
