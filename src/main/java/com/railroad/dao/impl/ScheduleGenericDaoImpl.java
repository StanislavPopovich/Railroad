package com.railroad.dao.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.model.ScheduleEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleGenericDaoImpl extends BaseGenericDao<ScheduleEntity, Long> implements ScheduleGenericDao {
    public ScheduleGenericDaoImpl() {
        super(ScheduleEntity.class);
    }

    @Override
    public List<ScheduleEntity> findScheduleByTrainId(Long trainId) {
        Session session = getSession();
        List<ScheduleEntity> scheduleEntity = session.createQuery("select s from ScheduleEntity s " +
                "where s.train_id=:trainId").setParameter("trainId", trainId).getResultList();
        return scheduleEntity;
    }

    @Override
    public List<ScheduleEntity> findScheduleByStationId(Long stationId) {
        Session session = getSession();
        List<ScheduleEntity> scheduleEntity = session.createQuery("select s from ScheduleEntity s " +
                "where s.station_id=:stationId").setParameter("stationId", stationId).getResultList();
        return scheduleEntity;
    }
}
