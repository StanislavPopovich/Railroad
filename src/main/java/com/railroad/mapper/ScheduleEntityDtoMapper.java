package com.railroad.mapper;

import com.railroad.dto.schedule.ScheduleDto;
import com.railroad.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleEntityDtoMapper {

    @Mapping(source = "arrivalDate", dateFormat = "yyyy-MM-dd HH:mm", target = "arrivalDate")
    @Mapping(source = "departDate", dateFormat = "yyyy-MM-dd HH:mm", target = "departDate")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "departDateFromFirstStation")
    @Mapping(source = "scheduleEntity.trainEntity.number", target = "trainNumber")
    @Mapping(source = "scheduleEntity.stationEntity.name", target = "stationName")
    ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity);

    List<ScheduleDto> scheduleEntitiesToScheduleDtos(List<ScheduleEntity> scheduleEntities);




}
