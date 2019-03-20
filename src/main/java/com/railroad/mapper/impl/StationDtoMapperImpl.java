package com.railroad.mapper.impl;

import com.railroad.dto.StationDto;
import com.railroad.mapper.api.StationDtoMapper;
import com.railroad.model.Station;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StationDtoMapperImpl implements StationDtoMapper {
    @Override
    public Station stationDtoToStation(StationDto stationDto) {
        if(stationDto == null){
            return null;
        } else {
        Station station = new Station();
        station.setName(stationDto.getName());
        return station;
        }
    }

    @Override
    public StationDto stationToStationDto(Station station) {
        if (station == null) {
            return null;
        } else {
            StationDto stationDto = new StationDto();
            stationDto.setName(station.getName());
            return stationDto;
        }
    }

    @Override
    public List<StationDto> stationsToStationDtos(List<Station> stations) {
        if (stations == null) {
            return null;
        } else {
            List<StationDto> stationDtos = new ArrayList<>();
            for(Station station: stations){
                StationDto stationDto = new StationDto();
                stationDto.setName(station.getName());
                stationDtos.add(stationDto);
            }
            return stationDtos;
        }
    }

    @Override
    public List<String> stationsToStationsName(List<Station> stations){
        if (stations == null) {
            return null;
        } else {
            List<String> stationNames = new ArrayList<>();
            for(Station station: stations){
                stationNames.add(station.getName());
            }
            return stationNames;
        }
    }
}
