package com.railroad.mapper;

import com.railroad.dto.way.WayDto;
import com.railroad.model.StationEntity;
import com.railroad.model.WayEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WayEntityDtoMapper {

    @Mapping(source = "firstStation", target ="firstStationEntity")
    @Mapping(source = "secondStation", target ="secondStationEntity")
    WayEntity wayDtoToWayEntity(WayDto wayDto);

    @Mapping(source = "wayEntity.firstStationEntity.name", target = "firstStation")
    @Mapping(source = "wayEntity.secondStationEntity.name", target = "secondStation")
    WayDto wayEntityToWayDto(WayEntity wayEntity);

    List<WayEntity> wayDtosToWayEntities(List<WayDto> wayDtos);
    List<WayDto> wayEntitiesToWayDtos(List<WayEntity> wayEntities);

    @Mapping(source = "stationName", target = "name")
    StationEntity stationNameToStationEntity(String stationName);

}
