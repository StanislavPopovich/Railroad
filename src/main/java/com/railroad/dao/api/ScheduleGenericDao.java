package com.railroad.dao.api;

import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;

import java.util.Date;
import java.util.List;

public interface ScheduleGenericDao extends GenericDao<ScheduleEntity, Long> {

    List<ScheduleEntity> findScheduleByStationIdAndDepartDate(Long stationId, Date date);

    List<ScheduleEntity> findScheduleByTrainAndDates(TrainEntity trainEntity, Date departDate, Date arrivalDate);



}
