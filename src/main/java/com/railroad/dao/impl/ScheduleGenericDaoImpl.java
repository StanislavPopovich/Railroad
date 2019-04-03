package com.railroad.dao.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dto.ScheduleDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class ScheduleGenericDaoImpl extends BaseGenericDao<ScheduleEntity, Long> implements ScheduleGenericDao {
    public ScheduleGenericDaoImpl() {
        super(ScheduleEntity.class);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<ScheduleEntity> findScheduleByStationIdAndDepartDate(Long stationId, Date date) {
        Session session = getSession();
        List<ScheduleEntity> scheduleEntities = session.createQuery("select s from ScheduleEntity s " +
                "where s.departDate > :dayBefore and s.departDate < :dayAfter and s.stationEntity.id= :id").
                setParameter("dayBefore", date).
                setParameter("dayAfter", new LocalDate(date).plusDays(1).toDate()).
                setParameter("id", stationId).list();
        return scheduleEntities;
    }

    @Override
    public ScheduleEntity getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date date) {
        Session session = getSession();
        ScheduleEntity scheduleEntity = (ScheduleEntity)session.createQuery("select s from ScheduleEntity s " +
                "where s.trainEntity= : train and s.departDate > :dayBefore and s.departDate < :dayAfter").
                setParameter("train", trainEntity).
                setParameter("dayBefore", date).
                setParameter("dayAfter", new LocalDate(date).plusDays(1).toDate()).
                uniqueResult();
        return scheduleEntity;
    }

}
