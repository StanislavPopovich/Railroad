package com.railroad.mapper;

import com.railroad.dto.schedule.ScheduleInfoDto;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleInfoDtoMapper {

    @Mapping(source = "arrivalDate", dateFormat = "HH:mm", target = "arrivalDate")
    @Mapping(source = "departDate", dateFormat = "HH:mm", target = "departDate")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "departDateFromFirstStation")
    @Mapping(source = "scheduleEntity.trainEntity.number", target = "train")
    @Mapping(source = "scheduleEntity.stationEntity.name", target = "station")
    @Mapping(source = "scheduleEntity.trainEntity.stationEntities", target = "stations")
    ScheduleInfoDto scheduleEntityToScheduleInfoDto(ScheduleEntity scheduleEntity);

    default List<String> stationEntityToString(List<StationEntity> stationEntities){
        List<String> stations = new ArrayList<>();
        for(StationEntity station: stationEntities){
            stations.add(station.getName());
        }
        return stations;
    }

    List<ScheduleInfoDto> scheduleEntitieToScheduleInfoDtos(List<ScheduleEntity> scheduleEntities);
}
