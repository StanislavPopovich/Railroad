package com.railroad.service.api;

import com.railroad.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    void save(ScheduleDto scheduleDto);
    List<ScheduleDto> getScheduleByStationName(String stationName);
    List<ScheduleDto> getScheduleByTrainNumber(Integer trainNumber);
}
