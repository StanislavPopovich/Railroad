package com.railroad.mapper;

import com.railroad.dto.ScheduleDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleEntityDtoMapper {

    @Mapping(source = "arrivalDate", target = "arrivalDate")
    @Mapping(source = "departDate", target = "departDate")
    @Mapping(source = "stationName", target = "stationEntity")
    @Mapping(source = "trainNumber", target = "trainEntity")
    ScheduleEntity scheduleDtoToScheduleEntity(ScheduleDto scheduleDto);

    @Mapping(source = "arrivalDate", target = "arrivalDate")
    @Mapping(source = "departDate", target = "departDate")
    @Mapping(source = "scheduleEntity.stationEntity.name", target = "stationName")
    @Mapping(source = "scheduleEntity.trainEntity.number", target = "trainNumber")
    ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity);

    @Mapping(source = "stationName", target = "name")
    StationEntity stationNameToStationEntity(String stationName);

    @Mapping(source = "trainNumber", target = "number")
    TrainEntity trainNumberToTrainEntity(Integer trainNumber);

    List<ScheduleDto> scheduleEntitiesToScheduleDtos(List<ScheduleEntity> scheduleEntities);


}
