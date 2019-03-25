package com.railroad.dao.impl;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.model.TrainEntity;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class TrainGenericDaoImpl extends BaseGenericDao<TrainEntity, Long> implements TrainGenericDao {
    public TrainGenericDaoImpl() {
        super(TrainEntity.class);
    }
}
