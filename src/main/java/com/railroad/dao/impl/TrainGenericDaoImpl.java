package com.railroad.dao.impl;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class TrainGenericDaoImpl extends BaseGenericDao<TrainEntity, Long> implements TrainGenericDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(TrainGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public TrainGenericDaoImpl() {
        super(TrainEntity.class);
    }


    /**
     * Returns entity from db by number
     * @param trainNumber train number
     * @return train
     */
    @Override
    public TrainEntity findTrainByNumber(Integer trainNumber) throws RailroadDaoException {
        TrainEntity trainEntity;
        try{
            trainEntity = (TrainEntity)entityManager.createQuery("select t from TrainEntity t where t.number=:trainNumber").
                    setParameter("trainNumber", trainNumber).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in TrainGenericDaoImpl - findTrainByNumber().");
            throw new RailroadDaoException(e);
        }
        return trainEntity;
    }

    /**
     * Returns all number of trains from db
     * @return list of trains numbers
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getAllTrainsNumbers() throws RailroadDaoException {
        List<Integer> numbers;
        try{
            numbers = entityManager.createQuery("select t.number from TrainEntity t " +
                    "order by t.number").getResultList();
        }catch (Exception e){
            logger.warn("Exception in TrainGenericDaoImpl - getAllTrainsNumbers().");
            throw new RailroadDaoException(e);
        }
        return numbers;
    }


    /**
     * Getting 1 if train is exist in database
     * @param trainNumber train number
     * @return
     */
    @Override
    public Long getCountTrains(Integer trainNumber) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(t) from " +
                    "TrainEntity t where t.number= :number").setParameter("number", trainNumber).
                    getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in TrainGenericDaoImpl - getCountTrains().");
            throw new RailroadDaoException(e);
        }
        return count;
    }

    /**
     * Getting all trains by station
     * @param departStation station
     * @return List of TrainEntity
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TrainEntity> getAllByDepartStation(StationEntity departStation)
            throws RailroadDaoException {
        List<TrainEntity> trains;
        try{
            trains = entityManager.createQuery("select t from TrainEntity t join fetch " +
                    "t.stationEntities s where s= :departStation").
                    setParameter("departStation", departStation).getResultList();
        }catch (Exception e){
            logger.warn("Exception in TrainGenericDaoImpl - getAllByDepartStation().");
            throw new RailroadDaoException(e);
        }
        return trains;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getTrainsIdByDepartAndArrivalStations(StationEntity departStation,
                                                                   StationEntity arrivalStation)
            throws RailroadDaoException {
        List<Long> trainsId = new ArrayList<>();
        List<BigInteger> trains;
        try{
            String query = "select train_id from train_stations train join (select train_id as id from train_stations " +
                    "where station_id=" + arrivalStation.getId() +") trainArrivalStation on " +
                    "trainArrivalStation.id=train.train_id " +
                    "where station_id=" + departStation.getId();
            trains = entityManager.createNativeQuery(query).getResultList();
        }catch (Exception e){
            logger.warn("Exception in TrainGenericDaoImpl - getTrainsIdByDepartAndArrivalStations().");
            throw new RailroadDaoException(e);
        }
        for(BigInteger id: trains){
            trainsId.add(id.longValue());
        }
        return trainsId;
    }

    @Override
    public List<TrainEntity> getAll() throws RailroadDaoException {
        List<TrainEntity> trains;
        try{
            trains = entityManager.createQuery("select t from TrainEntity t order by t.number").
                    getResultList();
        }catch (Exception e){
            logger.warn("Exception in TrainGenericDaoImpl - getAll().");
            throw new RailroadDaoException(e);
        }
       return trains;
    }
}
