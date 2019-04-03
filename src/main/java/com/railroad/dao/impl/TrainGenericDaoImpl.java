package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO implementation for the {@link TrainEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class TrainGenericDaoImpl extends BaseGenericDao<TrainEntity, Long> implements TrainGenericDao {
    private static final Logger logger = Logger.getLogger(TrainGenericDaoImpl.class);
    public TrainGenericDaoImpl() {
        super(TrainEntity.class);
    }

    @Autowired
    private StationGenericDao stationGenericDao;

    @Override
    public TrainEntity findTrainByNumber(Integer trainNumber) {
        Session session = getSession();
        TrainEntity trainEntity = (TrainEntity)session.createQuery("select t from TrainEntity t where t.number=:trainNumber").
                setParameter("trainNumber", trainNumber).uniqueResult();
        return trainEntity;
    }
}
