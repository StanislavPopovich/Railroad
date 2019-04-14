package com.railroad.mapper;

import com.railroad.dto.TrainDto;
import com.railroad.dto.TrainScheduleDto;
import com.railroad.dto.TrainSearchDto;
import com.railroad.dto.TrainTicketDto;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Mapper(componentModel = "spring")
public interface TrainEntityDtoMapper {

    @Mapping(source = "stations", target = "stationEntities")
    TrainEntity trainDtoToTrainEntity(TrainDto trainDto);

    @Mapping(source = "stationEntities", target = "stations")
    TrainDto trainEntityToTrainDto(TrainEntity trainEntity);

    TrainEntity trainSearchDtoToTrainEntity(TrainSearchDto trainSearchDto);
    TrainSearchDto trainEntityToTrainSearchDto(TrainEntity trainEntity);

    @Mapping(source = "stations", target = "stationEntities")
    TrainEntity trainTicketDtoToTrainEntity(TrainTicketDto trainTicketDto);
    @Mapping(source = "stationEntities", target = "stations")
    TrainTicketDto trainEntityToTrainTicketDto(TrainEntity trainEntity);

    TrainScheduleDto trainEntityToTrainScheduleDto(TrainEntity trainEntity);

    List<TrainDto> trainEntitiesToTrainDto(List<TrainEntity> trainEntities);

    default LinkedList<String> stationEntitiesToStationsNames(List<StationEntity> stationEntities){
        LinkedList<String> stations = new LinkedList<>();
        for(StationEntity stationEntity: stationEntities){
            stations.add(stationEntity.getName());
        }
        return stations;
    }
    default List<StationEntity> stationsNamesToStationsEntities(List<String> stations){
        List<StationEntity> stationEntities = new ArrayList<>();
        for(String station: stations){
            StationEntity stationEntity = new StationEntity();
            stationEntity.setName(station);
            stationEntities.add(stationEntity);
        }
        return stationEntities;
    }

}
