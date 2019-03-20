package com.railroad.dao.impl;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.model.Train;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link com.railroad.model.Train} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class TrainGenericDaoImpl extends BaseGenericDao<Train, Long> implements TrainGenericDao {
    public TrainGenericDaoImpl() {
        super(Train.class);
    }
}
