package com.railroad.dao.api;

import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;

import java.util.List;


/**
 * DAO for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TrainGenericDao extends GenericDao<TrainEntity, Long> {

    /**
     * Returns entity from db by number
     * @param trainNumber
     * @return train
     */
    TrainEntity findTrainByNumber(Integer trainNumber);

    /**
     * Returns all number of trains from db
     * @return list of trains numbers
     */
    List<Integer> getAllTrainsNumbers();


    Long getCountTrains(Integer trainNumber);

    List<TrainEntity> getAllByDepartStation(StationEntity departStation);
    List<TrainEntity> getAllByDepartAndArrivalStation(StationEntity departStation, StationEntity arrivalStation);
}
