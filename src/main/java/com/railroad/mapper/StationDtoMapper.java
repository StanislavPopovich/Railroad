package com.railroad.mapper;

import com.railroad.dto.StationDto;
import com.railroad.model.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationDtoMapper {

    @Mapping(source = "name", target = "name")
    Station stationDtoToStation(StationDto stationDto);

    @Mapping(source = "name", target = "name")
    StationDto stationToStationDto(Station station);

    List<StationDto> stationsToDtos(List<Station> stations);
    List<Station> stationDtosToStations(List<StationDto> stationDtos);
}
