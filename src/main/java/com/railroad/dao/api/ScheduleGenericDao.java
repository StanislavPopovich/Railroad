package com.railroad.dao.api;

import com.railroad.model.ScheduleEntity;

import java.util.List;

public interface ScheduleGenericDao extends GenericDao<ScheduleEntity, Long> {

    List<ScheduleEntity> findScheduleByTrainId(Long trainId);
    List<ScheduleEntity> findScheduleByStationId(Long stationId);
}
