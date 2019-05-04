package com.railroad.mapper;

import com.railroad.dto.station.StationDto;
import com.railroad.model.StationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationEntityDtoMapper {
    StationEntity stationDtoToStationEntity(StationDto stationDto);
    StationDto stationEntityToStationDto(StationEntity stationEntity);
    List<StationDto> stationEntitiesToStationDtos(List<StationEntity> stationEntities);
}
