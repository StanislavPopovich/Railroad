package com.railroad.mapper.api;

import com.railroad.dto.StationDto;
import com.railroad.model.Station;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StationDtoMapper {
    Station stationDtoToStation(StationDto stationDto);
    StationDto stationToStationDto(Station station);
    List<StationDto> stationsToStationDtos(List<Station> stations);
    List<String> stationsToStationsName(List<Station> stations);
}

