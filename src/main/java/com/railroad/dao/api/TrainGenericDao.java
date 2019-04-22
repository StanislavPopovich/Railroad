package com.railroad.dao.api;

import com.railroad.model.TrainEntity;

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

    /**
     * Returns of entities that matches by number
     * @param trainEntity
     * @return 0 or 1
     */
    Long getCountTrains(TrainEntity trainEntity);
}
