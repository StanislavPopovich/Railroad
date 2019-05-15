package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO implementation for the {@link StationEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class StationGenericDaoImpl extends BaseGenericDao<StationEntity, Long> implements StationGenericDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(StationGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public StationGenericDaoImpl() {
        super(StationEntity.class);
    }

    /**
     * Returns an entity from db by name
     *
     * @param name
     * @return StationEntity with selected name
     */
    @Override
    public StationEntity findByStationName(String name) throws RailroadDaoException {
        StationEntity stationEntity;
        try{
            stationEntity = (StationEntity) entityManager.createQuery("select s from StationEntity s " +
                    "where s.name= :name").
                    setParameter("name", name).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in StationGenericDaoImpl - findByStationName().");
            throw new RailroadDaoException(e);
        }
        return stationEntity;
    }

    /**
     * Returns all names of entities from db
     * @return list names of entities
     */
    @Override
    public List<String> getAllStationNames() throws RailroadDaoException {
        List<String> stationNames;
        try{
            stationNames = entityManager.createQuery("select s.name from StationEntity s " +
                    "order by s.name").getResultList();
        }catch (Exception e){
            logger.warn("Exception in StationGenericDaoImpl - getAllStationNames().");
            throw new RailroadDaoException(e);
        }
        return stationNames;
    }

    /**
     * Returns last id of entity from db
     * @return id
     */
    @Override
    public int getIdOfLastStation() throws RailroadDaoException {
        StationEntity stationEntity;
        try{
            stationEntity = (StationEntity)entityManager.
                    createQuery("select s from StationEntity s order by s.id desc ").
                    setMaxResults(1).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in StationGenericDaoImpl - getIdOfLastStation().");
            throw new RailroadDaoException(e);
        }
        return stationEntity.getId().intValue();
    }

    /**
     * Getting 1 if station is exist in database
     * @param stationName station
     * @return Long
     */
    @Override
    public Long getCountStations(String stationName) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(s) from " +
                    "StationEntity s where s.name= :name").setParameter("name", stationName).
                    getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in StationGenericDaoImpl - getCountStations().");
            throw new RailroadDaoException(e);
        }
        return count;
    }
}
