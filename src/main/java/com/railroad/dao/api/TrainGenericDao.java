package com.railroad.dao.api;

import com.railroad.model.TrainEntity;


/**
 * DAO for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TrainGenericDao extends GenericDao<TrainEntity, Long> {

    TrainEntity findTrainByNumber(Integer trainNumber);
}
