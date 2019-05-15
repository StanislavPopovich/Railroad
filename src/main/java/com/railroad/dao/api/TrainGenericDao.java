package com.railroad.dao.api;

import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;


/**
 * DAO for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TrainGenericDao extends GenericDao<TrainEntity, Long> {

    TrainEntity findTrainByNumber(Integer trainNumber) throws RailroadDaoException;

    List<Integer> getAllTrainsNumbers() throws RailroadDaoException;

    Long getCountTrains(Integer trainNumber) throws RailroadDaoException;

    List<TrainEntity> getAllByDepartStation(StationEntity departStation) throws RailroadDaoException;

    List<Long> getTrainsIdByDepartAndArrivalStations(StationEntity departStation, StationEntity arrivalStation) throws RailroadDaoException;

}
