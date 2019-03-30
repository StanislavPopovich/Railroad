package com.railroad.dao.api;

import com.railroad.model.TrainEntity;

import java.util.List;

/**
 * DAO for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TrainGenericDao extends GenericDao<TrainEntity, Long> {

    List<TrainEntity > getTrainsByStations(Long startStationId, Long destStationId);
    TrainEntity findTrainByNumber(Integer trainNumber);
}
