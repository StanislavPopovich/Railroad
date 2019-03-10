package com.railroad.mapper;

import com.railroad.dto.StationDto;
import com.railroad.model.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StationDtoMapper {

    @Mapping(source = "name", target = "name")
    Station StationDtoToStation(StationDto stationDto);

    @Mapping(source = "name", target = "name")
    StationDto StationToStationDto(Station station);
}
