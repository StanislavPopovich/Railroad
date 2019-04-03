package com.railroad.dao.api;

import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;

import java.util.Date;
import java.util.List;

public interface ScheduleGenericDao extends GenericDao<ScheduleEntity, Long> {

    List<ScheduleEntity> findScheduleByStationIdAndDate(Long stationId, Date date);

    ScheduleEntity getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date date);

}
