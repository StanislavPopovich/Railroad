package com.railroad.dao.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class ScheduleGenericDaoImpl extends BaseGenericDao<ScheduleEntity, Long> implements ScheduleGenericDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(ScheduleGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public ScheduleGenericDaoImpl() {
        super(ScheduleEntity.class);
    }

    /**
     * Finding schedule for station on departure date
     * @param stationEntity station
     * @param date departure date
     * @return List of ScheduleEntity
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ScheduleEntity> findScheduleByStationAndDepartDate(StationEntity stationEntity, Date date)
            throws RailroadDaoException {
        List<ScheduleEntity> scheduleEntities;
        try{
            scheduleEntities = entityManager.createQuery("select s from ScheduleEntity s " +
                    "where s.departDate > :dayBefore and s.departDate < :dayAfter and s.stationEntity= :station " +
                    "order by s.arrivalDate").
                    setParameter("dayBefore", date).
                    setParameter("dayAfter", new LocalDate(date).plusDays(1).toDate()).
                    setParameter("station", stationEntity).getResultList();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - findScheduleByStationAndDepartDate().");
            throw new RailroadDaoException(e);
        }
        return scheduleEntities;
    }

    /**
     * Finding schedule for train by departure date
     * @param trainEntity train
     * @param departDate daparture date
     * @return ScheduleEntity
     */
    @Override
    public ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate)
            throws RailroadDaoException {
        ScheduleEntity scheduleEntity;
        try{
            scheduleEntity = (ScheduleEntity) entityManager.createQuery("select s from ScheduleEntity s " +
                    "where s.trainEntity= :train and s.departDate= :departDate").
                    setParameter("train", trainEntity).
                    setParameter("departDate", departDate).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - findScheduleByTrainAndDepartDate().");
            throw new RailroadDaoException(e);
        }
        return scheduleEntity;
    }

    /**
     * Finding schedule for train by arrival date
     * @param trainEntity train
     * @param arrivalDate arrival date
     * @return ScheduleEntity
     */
    @Override
    public ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate)
            throws RailroadDaoException {
        ScheduleEntity scheduleEntity;
        try{
            scheduleEntity = (ScheduleEntity) entityManager.createQuery("select s from ScheduleEntity s " +
                    "where s.trainEntity= :train and s.arrivalDate= :arrivalDate").
                    setParameter("train", trainEntity).
                    setParameter("arrivalDate", arrivalDate).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - findScheduleByTrainAndArrivalDate().");
            throw new RailroadDaoException(e);
        }
        return scheduleEntity;
    }

    /**
     * Finding schedule for train by departure date from first station of route
     * @param trainEntity train
     * @param departDateFromFirstStation departure date from first station of train route
     * @return List of ScheduleEntities
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDateFromFirstStation)
            throws RailroadDaoException {
        List<ScheduleEntity> scheduleEntity;
        try{
            scheduleEntity = entityManager.createQuery("select s from ScheduleEntity s " +
                    "where s.trainEntity= :train and s.departDateFromFirstStation= :departDateFromFirstStation").
                    setParameter("train", trainEntity).
                    setParameter("departDateFromFirstStation", departDateFromFirstStation).getResultList();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - findSchedulesForTrain().");
            throw new RailroadDaoException(e);
        }
        return scheduleEntity;
    }

    /**
     * Getting departure dates for train
     * @param trainEntity train
     * @return List of Date
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Date> getDepartDatesForTrain(TrainEntity trainEntity) throws RailroadDaoException {
        List<Date> departDates;
        try{
            departDates = entityManager.createQuery("select s.departDateFromFirstStation from " +
                    "ScheduleEntity s where s.trainEntity= :train and s.departDateFromFirstStation >= :currentDate " +
                    "group by s.departDateFromFirstStation order by s.departDateFromFirstStation").
                    setParameter("currentDate", getCurrentDate()).
                    setParameter("train", trainEntity).getResultList();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - getDepartDatesForTrain().");
            throw new RailroadDaoException(e);
        }
        return departDates;
    }

    /**
     * Delete schedule for train by departure date from first station of route
     * @param trainEntity train
     * @param departDate departure date from first station of train route
     */
    @Override
    @SuppressWarnings("unchecked")
    public void removeScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate)
            throws RailroadDaoException {
        try{
            entityManager.createQuery("delete  from ScheduleEntity s where s.trainEntity= :train and " +
                    "s.departDateFromFirstStation= :departDate").setParameter("train", trainEntity).
                    setParameter("departDate", departDate).executeUpdate();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - removeScheduleByTrainAndDepartDate().");
            throw new RailroadDaoException(e);
        }
    }

    /**
     * Getting schedule for train by station and departure date from first staion of route
     * @param trainEntity train
     * @param stationEntity station
     * @param departDate departure date from first station of train route
     * @return ScheduleEntity
     */
    @Override
    public ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity,
                                                              Date departDate) throws RailroadDaoException {
        ScheduleEntity scheduleEntity;
        try{
            scheduleEntity = (ScheduleEntity) entityManager.createQuery("select s from ScheduleEntity s " +
                    "where s.trainEntity= :train and s.stationEntity= :station and s.departDateFromFirstStation= " +
                    ":departDate").setParameter("train", trainEntity).
                    setParameter("station", stationEntity).
                    setParameter("departDate", departDate).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - getScheduleByTrainAndStationAndDate().");
            throw new RailroadDaoException(e);
        }
        return scheduleEntity;
    }

    /**
     * Getting actual schedule
     * @param currentDate current date
     * @return List of ScheduleEntities
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ScheduleEntity> getActualSchedules(Date currentDate) throws RailroadDaoException {
        List<ScheduleEntity> scheduleEntity;
        try{
            scheduleEntity =  entityManager.createQuery("select s from ScheduleEntity s " +
                    "where s.arrivalDate >= :currentDate and s.arrivalDate < :dayAfter " +
                    "order by s.arrivalDate").
                    setParameter("currentDate", currentDate).
                    setParameter("dayAfter", new LocalDate(currentDate).plusDays(1).toDate()).getResultList();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - getActualSchedules().");
            throw new RailroadDaoException(e);
        }
        return scheduleEntity;
    }

    /**
     * Getting trains numbers which contains in schedule
     * @return List Integer
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getTrainsNumberFromSchedule() throws RailroadDaoException {
        List<Integer> numbers;
        try{
            numbers = entityManager.createQuery("select s.trainEntity.number from ScheduleEntity s " +
                    " where s.departDateFromFirstStation >= :currentDate group by s.trainEntity.number order by " +
                    "s.trainEntity.number").
                    setParameter("currentDate", getCurrentDate()).getResultList();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - getTrainsNumberFromSchedule().");
            throw new RailroadDaoException(e);
        }
        return numbers;
    }

    /**
     * Getting 1 if train is exist in database
     * @param trainEntity train
     * @param date deoarture date
     * @return Long
     */
    @Override
    public Long isExistTrainInScheduleByDate(TrainEntity trainEntity, Date date) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(s) from ScheduleEntity s where " +
                    "s.trainEntity>= :train and s.departDate >= :date").
                    setParameter("train", trainEntity).setParameter("date", date).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - isExistTrainInScheduleByDate().");
            throw new RailroadDaoException(e);
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long isScheduleExistByTrainAndDay(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(s) from ScheduleEntity s " +
                    "where s.departDate > :startDay and s.departDate < :endDay and s.trainEntity= :train").
                    setParameter("startDay", departDate).
                    setParameter("endDay", new LocalDate(departDate).plusDays(1).toDate()).
                    setParameter("train", trainEntity).
                    getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in ScheduleGenericDaoImpl - isScheduleExistByTrainAndDay().");
            throw new RailroadDaoException(e);
        }
        return count;
    }


}
