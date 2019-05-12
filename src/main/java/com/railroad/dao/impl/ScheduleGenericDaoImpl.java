package com.railroad.dao.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
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
    public List<ScheduleEntity> findScheduleByStationAndDepartDate(StationEntity stationEntity, Date date) {
        List<ScheduleEntity> scheduleEntities = entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.departDate > :dayBefore and s.departDate < :dayAfter and s.stationEntity= :station " +
                "order by s.arrivalDate").
                setParameter("dayBefore", date).
                setParameter("dayAfter", new LocalDate(date).plusDays(1).toDate()).
                setParameter("station", stationEntity).getResultList();
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
    public List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDateFromFirstStation){
        List<ScheduleEntity> scheduleEntity = entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.trainEntity= :train and s.departDateFromFirstStation= :departDateFromFirstStation").
                setParameter("train", trainEntity).
                setParameter("departDateFromFirstStation", departDateFromFirstStation).getResultList();
        return scheduleEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Date> getDepartDatesForTrain(TrainEntity trainEntity) {
        List<Date> departDates = entityManager.createQuery("select s.departDateFromFirstStation from " +
                "ScheduleEntity s where s.trainEntity= :train and s.departDateFromFirstStation >= :currentDate " +
                "group by s.departDateFromFirstStation").setParameter("currentDate", getCurrentDate()).
                setParameter("train", trainEntity).getResultList();
        return departDates;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        entityManager.createQuery("delete  from ScheduleEntity s where s.trainEntity= :train and " +
                "s.departDateFromFirstStation= :departDate").setParameter("train", trainEntity).
                setParameter("departDate", departDate).executeUpdate();
    }

    @Override
    public ScheduleEntity getScheduleBuTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity, Date departDate) {
        ScheduleEntity scheduleEntity = (ScheduleEntity) entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.trainEntity= :train and s.stationEntity= :station and s.departDateFromFirstStation= :departDate").
                setParameter("train", trainEntity).
                setParameter("station", stationEntity).
                setParameter("departDate", departDate).getSingleResult();
        return scheduleEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ScheduleEntity> getActualSchedules(Date currentDate) {
        List<ScheduleEntity> scheduleEntity =  entityManager.createQuery("select s from ScheduleEntity s " +
                "where s.arrivalDate >= :currentDate and s.arrivalDate < :dayAfter " +
                "order by s.arrivalDate").
                setParameter("currentDate", currentDate).
                setParameter("dayAfter", new LocalDate(currentDate).plusDays(1).toDate()).getResultList();
        return scheduleEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getTrainsNumberFromSchedule() {
        List<Integer> numbers = entityManager.createQuery("select s.trainEntity.number from ScheduleEntity s " +
                " where s.departDateFromFirstStation >= :currentDate group by s.trainEntity.number order by " +
                "s.trainEntity.number").
                setParameter("currentDate", getCurrentDate()).getResultList();
        return numbers;
    }


}
