package com.railroad.mapper;

import com.railroad.dto.TrainDto;
import com.railroad.dto.TrainScheduleDto;
import com.railroad.dto.TrainTargetDto;
import com.railroad.dto.TrainTicketDto;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainEntityDtoMapper {

    @Mapping(source = "stations", target = "stationEntities")
    TrainEntity trainDtoToTrainEntity(TrainDto trainDto);

    @Mapping(source = "stationEntities", target = "stations")
    TrainDto trainEntityToTrainDto(TrainEntity trainEntity);

    TrainTargetDto trainEntityToTrainSearchDto(TrainEntity trainEntity);


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
