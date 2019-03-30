package com.railroad.dao.impl;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.model.TrainEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    @Override
    public List<TrainEntity> getTrainsByStations(Long startStationId, Long destStationId) {
        Session session = getSession();
        List<TrainEntity> trains = session.createNativeQuery("select * from trains  inner join " +
                "(select * from train_stations where station_id = :destStation) s  " +
                "inner join (select * from train_stations where station_id = :startStation) ss " +
                "on s.train_id = ss.train_id " +
                "on trains.id = s.train_id", TrainEntity.class).
                setParameter("destStation", destStationId).
                setParameter("startStation", startStationId).getResultList();
        return trains;
    }

    @Override
    public TrainEntity findTrainByNumber(Integer trainNumber) {
        Session session = getSession();
        TrainEntity trainEntity = (TrainEntity)session.createQuery("select t from TrainEntity t where t.number=:trainNumber").
                setParameter("trainNumber", trainNumber).uniqueResult();
        return trainEntity;
    }
}
