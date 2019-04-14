package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dto.StationDto;
import com.railroad.mapper.StationEntityDtoMapper;
import com.railroad.model.StationEntity;
import com.railroad.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private StationEntityDtoMapper stationDtoMapper;


    @Override
    @Transactional
    // +
    public void save(StationDto stationDto) {
        StationEntity stationEntity = stationDtoMapper.stationDtoToStationEntity(stationDto);
        stationGenericDao.save(stationEntity);
    }

    @Override
    public List<StationDto> getAll() {
        return stationDtoMapper.stationEntitiesToStationDtos(stationGenericDao.getAll());
    }

    @Override
    //+
    public List<String> getAllStationsName() {
        return stationGenericDao.getAllStationNames();
    }

    @Override
    public StationEntity getStationEntityByStationName(String name) {
        return stationGenericDao.findByStationName(name);
    }


    @Override
    //+
    public StationDto getStationById(Long id) {
        StationEntity stationEntity = stationGenericDao.getById(id);
        return stationDtoMapper.stationEntityToStationDto(stationEntity);
    }

    @Override
    public int getIdOfLastStation() {
        return stationGenericDao.getIdOfLastStation();
    }

}
