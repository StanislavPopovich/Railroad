package com.railroad.dao.api;

import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import java.util.Date;
import java.util.List;

public interface ScheduleGenericDao extends GenericDao<ScheduleEntity, Long> {

    List<ScheduleEntity> findScheduleByStationAndDepartDate(StationEntity stationEntity, Date date);
    ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);
    ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate);
    List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDateFromFirstStation);

    List<Date> getDepartDatesForTrain(TrainEntity trainEntity);

    void removeScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);
    ScheduleEntity getScheduleBuTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity,
                                                       Date departDate);

    List<ScheduleEntity> getActualSchedules(Date currentDate);

    List<Integer> getTrainsNumberFromSchedule();

}
