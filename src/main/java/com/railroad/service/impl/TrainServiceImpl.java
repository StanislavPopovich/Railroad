package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.TrainDto;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainServiceImpl implements TrainService {


    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private StationGenericDao stationDao;

    @Autowired
    private TrainEntityDtoMapper trainDtoMapper;


    @Override
    public void save(TrainDto trainDto) {
        TrainEntity trainEntity = trainDtoMapper.trainDtoToTrainEntity(trainDto);
        List<StationEntity> stations = new ArrayList<>();
        for(StationEntity stationEntity: trainEntity.getStationEntities()){
            stations.add(stationDao.findByStationName(stationEntity.getName()));
        }
        trainEntity.setStationEntities(stations);
        trainGenericDao.save(trainEntity);
    }

    @Override
    public List<TrainDto> getAll() {
        return trainDtoMapper.trainEntitiesToTrainDto(trainGenericDao.getAll());
    }
}
