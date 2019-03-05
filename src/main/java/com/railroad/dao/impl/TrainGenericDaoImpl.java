package com.railroad.dao.impl;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.model.Train;
import org.springframework.stereotype.Repository;

@Repository
public class TrainGenericDaoImpl extends BaseGenericDao<Train, Long> implements TrainGenericDao {
    public TrainGenericDaoImpl() {
        super(Train.class);
    }
}
