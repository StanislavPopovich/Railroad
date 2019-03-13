package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.WayGenericDao;
import com.railroad.dto.WayDto;
import com.railroad.model.Station;
import com.railroad.model.Way;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WayServiceImpl implements WayService {

    @Autowired
    private WayGenericDao wayGenericDao;

    @Autowired
    private StationGenericDao stationGenericDao;

    @Override
    @Transactional
    public void save(WayDto wayDto) {
        Way way = new Way();
        way.setFirstStation(stationGenericDao.findByStationName(wayDto.getFirstStation()));
        way.setSecondStation(stationGenericDao.findByStationName(wayDto.getSecondStation()));
        way.setDistance(new Double(wayDto.getDistance()));
        wayGenericDao.save(way);

    }

    @Override
    public List<WayDto> getAll() {
        List<Way> ways = wayGenericDao.getAll();
        List<WayDto> wayDtos = new ArrayList<>();
        for(Way way: ways){
            WayDto wayDto = new WayDto();
            wayDto.setFirstStation(way.getFirstStation().getName());
            wayDto.setSecondStation(way.getSecondStation().getName());
            wayDto.setDistance(way.getDistance().toString());
            wayDtos.add(wayDto);
        }
      return wayDtos;
    }
}
