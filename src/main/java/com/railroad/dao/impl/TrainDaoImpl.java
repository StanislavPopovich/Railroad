package com.railroad.dao.impl;

import com.railroad.dao.TrainDao;
import com.railroad.model.Train;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDaoImpl extends BaseDao<Train, Long> implements TrainDao {
    public TrainDaoImpl() {
        super(Train.class);
    }
}
