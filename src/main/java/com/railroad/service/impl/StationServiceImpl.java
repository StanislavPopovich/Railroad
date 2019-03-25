package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dto.StationDto;
import com.railroad.mapper.StationEntityDtoMapper;
import com.railroad.model.StationEntity;
import com.railroad.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private StationEntityDtoMapper stationDtoMapper;


    @Override
    public void save(StationDto stationDto) {
        StationEntity stationEntity = stationDtoMapper.stationDtoToStationEntity(stationDto);
        stationGenericDao.save(stationEntity);
    }

    @Override
    public List<StationDto> getAll() {
        return stationDtoMapper.stationEntitiesToStationDtos(stationGenericDao.getAll());
    }

    @Override
    public List<String> getAllStationsName() {
        return getNames(stationDtoMapper.stationEntitiesToStationDtos(stationGenericDao.getAll()));
    }

    @Override
    public StationDto getStationByName(String stationName) {
        return stationDtoMapper.stationEntityToStationDto(stationGenericDao.findByStationName(stationName));
    }

    @Override
    public StationDto getStationById(Long id) {
        StationEntity stationEntity = stationGenericDao.getById(id);
        return stationDtoMapper.stationEntityToStationDto(stationEntity);
    }

    private List<String> getNames(List<StationDto> stationDtos){
        List<String> names = new ArrayList<>();
        for(StationDto stationDto: stationDtos){
            names.add(stationDto.getName());
        }
        return names;
    }
}
