package com.railroad.mapper.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dto.WayDto;
import com.railroad.mapper.api.WayDtoMapper;
import com.railroad.model.Way;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WayDtoMapperImpl implements WayDtoMapper {
    @Autowired
    private StationGenericDao stationGenericDao;

    @Override
    public Way wayDtoToWay(WayDto wayDto) {
        if(wayDto == null){
            return null;
        }else{
            Way way = new Way();
            way.setFirstStation(stationGenericDao.findByStationName(wayDto.getFirstStation()));
            way.setSecondStation(stationGenericDao.findByStationName(wayDto.getSecondStation()));
            way.setDistance(new Double(wayDto.getDistance()));
            return way;
        }
    }

    @Override
    public WayDto wayToWayDto(Way way) {
        if(way == null){
            return null;
        }else {
            WayDto wayDto = new WayDto();
            wayDto.setFirstStation(way.getFirstStation().getName());
            wayDto.setSecondStation(way.getSecondStation().getName());
            wayDto.setDistance(way.getDistance().toString());
            return wayDto;
        }
    }

    @Override
    public List<WayDto> waysToWayDtos(List<Way> ways) {
        if(ways == null){
            return null;
        }else {
            List<WayDto> wayDtos = new ArrayList<>();
            for(Way way: ways){
                wayDtos.add(wayToWayDto(way));
            }
            return wayDtos;
        }
    }
}
