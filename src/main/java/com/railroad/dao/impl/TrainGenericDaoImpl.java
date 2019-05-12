package com.railroad.dao.impl;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO implementation for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class TrainGenericDaoImpl extends BaseGenericDao<TrainEntity, Long> implements TrainGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TrainGenericDaoImpl() {
        super(TrainEntity.class);
    }


    @Override
    public TrainEntity findTrainByNumber(Integer trainNumber) {
        TrainEntity trainEntity = (TrainEntity)entityManager.createQuery("select t from TrainEntity t where t.number=:trainNumber").
                setParameter("trainNumber", trainNumber).getSingleResult();
        return trainEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getAllTrainsNumbers() {
        List<Integer> numbers = entityManager.createQuery("select t.number from TrainEntity t").getResultList();
        return numbers;
    }


    @Override
    public Long getCountTrains(Integer trainNumber) {
        Long count = (Long) entityManager.createQuery("select count(t) from " +
                "TrainEntity t where t.number= :number").setParameter("number", trainNumber).
                getSingleResult();
        return count;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<TrainEntity> getAllByDepartStation(StationEntity departStation) {
        List<TrainEntity> trains = entityManager.createQuery("select t from TrainEntity t join fetch " +
                "t.stationEntities s where s= :departStation").
                setParameter("departStation", departStation).getResultList();
        return trains;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<TrainEntity> getAllByDepartAndArrivalStation(StationEntity departStation,
                                                             StationEntity arrivalStation) {
        /*List<TrainEntity> trains = entityManager.createQuery("select t from TrainEntity t join " +
                "t.stationEntities arrivalStation where arrivalStation= :arrivalStation").
                setParameter("departStation", departStation).
                setParameter("arrivalStation", arrivalStation).getResultList();*/
        return null;
    }
}
