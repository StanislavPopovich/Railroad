package com.railroad.service.api;

import com.railroad.dto.schedule.*;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */
public interface ScheduleService {

    void save(ScheduleTrainDto scheduleTrainDto) throws RailroadDaoException;

    List<ScheduleEntity> getScheduleByStationAndDepartDate(String stationName, Date departDate) throws RailroadDaoException;

    List<ScheduleInfoDto> getScheduleDtosByStationAndDepartDate(String stationName, Date date) throws RailroadDaoException;

    List<ScheduleDto> getAll() throws RailroadDaoException;

    ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate) throws RailroadDaoException;

    List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    List<Date> getDepartDatesForTrain(TrainEntity trainEntity) throws RailroadDaoException;

    void removeSchedulesByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    void updateSchedule(ScheduleUpdateDto scheduleUpdateDto) throws RailroadDaoException;

    ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity,
                                                       Date departDate) throws RailroadDaoException;

    ScheduleUpdateDto getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    List<ScheduleMessageInfoDto> getActualSchedule(Date currentDate) throws RailroadDaoException;

    List<Integer> getTrainsNumberFromSchedule() throws RailroadDaoException;

    Long getScheduleByTrainAndDepartDay(TrainEntity trainEntity, Date departDay) throws RailroadDaoException;

}
