package com.railroad.dao.api;

import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.Date;
import java.util.List;

public interface ScheduleGenericDao extends GenericDao<ScheduleEntity, Long> {

    List<ScheduleEntity> findScheduleByStationAndDepartDate(StationEntity stationEntity, Date date) throws RailroadDaoException;

    ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate) throws RailroadDaoException;

    List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDateFromFirstStation) throws RailroadDaoException;

    List<Date> getDepartDatesForTrain(TrainEntity trainEntity) throws RailroadDaoException;

    void removeScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity,
                                                       Date departDate) throws RailroadDaoException;

    List<ScheduleEntity> getActualSchedules(Date currentDate) throws RailroadDaoException;

    List<Integer> getTrainsNumberFromSchedule() throws RailroadDaoException;

    Long isExistTrainInScheduleByDate(TrainEntity trainEntity, Date date) throws RailroadDaoException;

}
