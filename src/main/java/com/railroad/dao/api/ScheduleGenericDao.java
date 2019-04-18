package com.railroad.dao.api;

import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import java.util.Date;
import java.util.List;

public interface ScheduleGenericDao extends GenericDao<ScheduleEntity, Long> {

    List<ScheduleEntity> findScheduleByStationAndDepartDate(StationEntity stationEntity, Date date);



    ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);
    ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate);

    List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDateFromFirstStation);

}
