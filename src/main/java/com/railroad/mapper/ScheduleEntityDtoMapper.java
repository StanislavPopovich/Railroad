package com.railroad.mapper;

import com.railroad.dto.ScheduleDto;
import com.railroad.dto.ScheduleInfoDto;
import com.railroad.dto.ScheduleUpdateDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleEntityDtoMapper {
    @Mapping(source = "arrivalDate", dateFormat = "yyyy-MM-dd HH:mm", target = "arrivalDate")
    @Mapping(source = "departDate", dateFormat = "yyyy-MM-dd HH:mm", target = "departDate")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "departDateFromFirstStation")
    ScheduleEntity scheduleDtoToScheduleEntity(ScheduleDto scheduleDto);

    @Mapping(source = "arrivalDate", dateFormat = "yyyy-MM-dd HH:mm", target = "arrivalDate")
    @Mapping(source = "departDate", dateFormat = "yyyy-MM-dd HH:mm", target = "departDate")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "departDateFromFirstStation")
    @Mapping(source = "scheduleEntity.trainEntity.number", target = "trainNumber")
    @Mapping(source = "scheduleEntity.stationEntity.name", target = "stationName")
    ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity);

    @Mapping(source = "arrivalDate", dateFormat = "yyyy-MM-dd HH:mm", target = "arrivalDate")
    @Mapping(source = "departDate", dateFormat = "yyyy-MM-dd HH:mm", target = "departDate")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "departDateFromFirstStation")
    ScheduleEntity scheduleUpdateDtoToScheduleEntity(ScheduleUpdateDto scheduleUpdateDto);

    @Mapping(source = "arrivalDate", dateFormat = "yyyy-MM-dd HH:mm", target = "arrivalDate")
    @Mapping(source = "departDate", dateFormat = "yyyy-MM-dd HH:mm", target = "departDate")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "departDateFromFirstStation")
    @Mapping(source = "departDateFromFirstStation",
            dateFormat = "yyyy-MM-dd", target = "oldDepartDateFromFirstStation")
    @Mapping(source = "scheduleEntity.trainEntity.number", target = "trainNumber")
    @Mapping(source = "scheduleEntity.stationEntity.name", target = "stationName")
    ScheduleUpdateDto scheduleEntityToScheduleUpdateDto(ScheduleEntity scheduleEntity);

    List<ScheduleDto> scheduleEntitiesToScheduleDtos(List<ScheduleEntity> scheduleEntities);

    List<ScheduleUpdateDto> scheduleEntityToScheduleUpdateDto(List<ScheduleEntity> scheduleEntities);



}
