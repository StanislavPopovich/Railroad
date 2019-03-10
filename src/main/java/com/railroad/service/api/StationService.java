package com.railroad.service.api;

import com.railroad.dto.StationDto;
import com.railroad.model.Station;

import java.util.List;

public interface StationService {
    void save(StationDto stationDto);
    List<Station> getAll();
    Station getStationByName(StationDto stationDto);
}
