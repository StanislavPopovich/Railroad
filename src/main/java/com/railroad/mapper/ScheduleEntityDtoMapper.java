package com.railroad.mapper;

import com.railroad.dto.ScheduleDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleEntityDtoMapper {
    @Mapping(source = "data", target = "dataAndTime")
    ScheduleEntity scheduleDtoToScheduleEntity(ScheduleDto scheduleDto);
    @Mapping(source = "dataAndTime", target = "data")
    ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity);

    @Mapping(source = "stationName", target = "name")
    StationEntity stationNameToStationEntity(String stationName);
    @Mapping(source = "trainNumber", target = "number")
    TrainEntity trainNumberToTrainEntity(Integer trainNumber);

    List<ScheduleDto> scheduleEntitiesToScheduleDtos(List<ScheduleEntity> scheduleEntities);


}
