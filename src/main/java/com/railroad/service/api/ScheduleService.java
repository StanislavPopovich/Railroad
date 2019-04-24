package com.railroad.service.api;

import com.railroad.dto.ScheduleDto;
import com.railroad.dto.ScheduleInfoDto;
import com.railroad.dto.ScheduleUpdateDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * Service interface for {@link ScheduleEntity}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface ScheduleService {
    /**
     * The method sends ScheduleEntity to dao layer
     * @param scheduleDto
     */
    void save(ScheduleDto scheduleDto);

    /**
     * The method returns scheduleEntity for station on a specific date from dao layer
     * @param stationName
     * @param departDate
     * @return list of ScheduleEntities
     */
    List<ScheduleEntity> getSchedulesByStationNameAndDepartDate(String stationName, Date departDate);

    List<ScheduleInfoDto> getScheduleDtosByStationNameAndDepartDate(String stationName, Date date);


    /**
     * The method returns all scheduleDto from dao layer
     * @return list of ScheduleDto
     */
    List<ScheduleDto> getAll();

    ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);
    ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate);

    //all schedules for train
    List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDate);

    List<Date> getDepartDatesForTrain(TrainEntity trainEntity);

    void removeSchedulesByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);

    void updateSchedule(ScheduleEntity scheduleEntity);

    ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity,
                                                       Date departDate);

    List<ScheduleUpdateDto> getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);

}
