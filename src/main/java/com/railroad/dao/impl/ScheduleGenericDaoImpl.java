package com.railroad.dao.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dto.ScheduleDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class ScheduleGenericDaoImpl extends BaseGenericDao<ScheduleEntity, Long> implements ScheduleGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ScheduleGenericDaoImpl() {
        super(ScheduleEntity.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ScheduleEntity> findScheduleByStationIdAndDepartDate(Long stationId, Date date) {
        List<ScheduleEntity> scheduleEntities = entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.departDate > :dayBefore and s.departDate < :dayAfter and s.stationEntity.id= :id").
                setParameter("dayBefore", date).
                setParameter("dayAfter", new LocalDate(date).plusDays(1).toDate()).
                setParameter("id", stationId).getResultList();
        return scheduleEntities;
    }


    @Override
    public ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate){
        ScheduleEntity scheduleEntity = (ScheduleEntity) entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.trainEntity= :train and s.departDate= :departDate").
                setParameter("train", trainEntity).
                setParameter("departDate", departDate).getSingleResult();
        return scheduleEntity;
    }

    @Override
    public ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate){
        ScheduleEntity scheduleEntity = (ScheduleEntity) entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.trainEntity= :train and s.arrivalDate= :arrivalDate").
                setParameter("train", trainEntity).
                setParameter("arrivalDate", arrivalDate).getSingleResult();
        return scheduleEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDate, Date arrivalDate){
        List<ScheduleEntity> scheduleEntity = entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.trainEntity= :train and s.departDate >= :departDate and s.departDate <= :arrivalDate").
                setParameter("train", trainEntity).
                setParameter("departDate", departDate).
                setParameter("arrivalDate", arrivalDate).getResultList();
        return scheduleEntity;
    }






}
