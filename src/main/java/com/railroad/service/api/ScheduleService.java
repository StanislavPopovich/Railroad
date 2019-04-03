package com.railroad.service.api;

import com.railroad.dto.ScheduleDto;
import com.railroad.model.ScheduleEntity;

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
    List<ScheduleEntity> getScheduleByStationName(String stationName, Date departDate);

    /**
     * The method returns all scheduleDto from dao layer
     * @return list of ScheduleDto
     */
    List<ScheduleDto> getAll();
}
