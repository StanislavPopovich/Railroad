package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dto.StationDto;
import com.railroad.mapper.api.StationDtoMapper;
import com.railroad.model.Station;
import com.railroad.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private StationDtoMapper stationDtoMapper;


    @Override
    public void save(StationDto stationDto) {
        Station station = stationDtoMapper.stationDtoToStation(stationDto);
        stationGenericDao.save(station);
    }

    @Override
    public List<StationDto> getAll() {
        return stationDtoMapper.stationsToStationDtos(stationGenericDao.getAll());
    }

    @Override
    public List<String> getAllStationsName() {
        return stationDtoMapper.stationsToStationsName(stationGenericDao.getAll());
    }

    @Override
    public StationDto getStationByName(String stationName) {
        return stationDtoMapper.stationToStationDto(stationGenericDao.findByStationName(stationName));
    }

    @Override
    public StationDto getStationById(Long id) {
        Station station = stationGenericDao.getById(id);
        return stationDtoMapper.stationToStationDto(station);
    }
}
