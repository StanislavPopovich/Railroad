package com.railroad.dao.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.model.PassengerEntity;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * DAO implementation for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class PassengerGenericDaoImpl extends BaseGenericDao<PassengerEntity, Long> implements PassengerGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PassengerGenericDaoImpl() {
        super(PassengerEntity.class);
    }


    //Нужен ли этот метод???
    @Override
    public PassengerEntity findPassengerByLastNameAndBirthDate(String lastName, String name, Date birthDate) {
        PassengerEntity passengerEntity = (PassengerEntity)entityManager.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName and p.name= :name and p.birthDate= :birthDate").setParameter("lastName", lastName).
                setParameter("name", name).setParameter("birthDate", birthDate).getSingleResult();
        return passengerEntity;
    }

    @Override
    public Long getCountPassengerByNameAndBirthDate(PassengerEntity passengerEntity) {
        Long count = (Long) entityManager.createQuery("select count(p) from " +
                "PassengerEntity p where p.lastName= :lastName and p.name= :name " +
                "and p.birthDate = :birthDate").setParameter("lastName", passengerEntity.getLastName()).
                setParameter("name", passengerEntity.getName()).
                setParameter("birthDate", passengerEntity.getBirthDate()).getSingleResult();
        return count;
    }
}
