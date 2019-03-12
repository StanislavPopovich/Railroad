package com.railroad.service.api;

import com.railroad.dto.StationDto;

import java.util.List;

public interface StationService {
    void save(StationDto stationDto);
    List<StationDto> getAll();
    StationDto getStationByName(String stationName);
}
