package com.railroad.dao.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.model.PassengerEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

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

    @Override
    public PassengerEntity findPassengerByAllFields(PassengerEntity passenger) {
        PassengerEntity passengerEntity = (PassengerEntity)entityManager.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName and p.name= :name and p.birthDate= :birthDate").
                setParameter("lastName", passenger.getLastName()).
                setParameter("name", passenger.getName()).
                setParameter("birthDate", passenger.getBirthDate()).getSingleResult();
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

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> findPassengersByName(String name) {
        List<PassengerEntity> passengerEntity = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.name= :name").
                setParameter("name", name).getResultList();
        return passengerEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> findPassengersByLastName(String lastName) {
        List<PassengerEntity> passengerEntity = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName").
                setParameter("lastName", lastName).getResultList();
        return passengerEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> findPassengersByBirthDate(Date birthDate) {
        List<PassengerEntity> passengerEntity = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.birthDate= :birthDate").
                setParameter("birthDate", birthDate).getResultList();
        return passengerEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> findPassengersByLastNameAndName(String lastName, String name) {
        List<PassengerEntity> passengerEntity = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName and p.name= :name").
                setParameter("lastName", lastName).
                setParameter("name", name).
                getResultList();
        return passengerEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> findPassengersByLastNameAndBirthDate(String lastName, Date birthDate) {
        List<PassengerEntity> passengerEntity = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName and p.birthDate= :birthDate").
                setParameter("lastName", lastName).
                setParameter("birthDate", birthDate).
                getResultList();
        return passengerEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> findPassengersByNameAndBirthDate(String name, Date birthDate) {
        List<PassengerEntity> passengerEntity = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.lastName= :lastName and p.birthDate= :birthDate").
                setParameter(name, name).
                setParameter("birthDate", birthDate).
                getResultList();
        return passengerEntity;
    }

}
