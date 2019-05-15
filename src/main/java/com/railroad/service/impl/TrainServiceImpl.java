package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.train.TrainDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Stanislav Popovich
 */

@Service
public class TrainServiceImpl implements TrainService {


    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private StationGenericDao stationDao;

    @Autowired
    private TrainEntityDtoMapper trainDtoMapper;


    /**
     * Saving a new entity to database
     * @param trainDto train data transfer object
     */
    @Override
    public void save(TrainDto trainDto) throws RailroadDaoException {
        TrainEntity trainEntity = trainDtoMapper.trainDtoToTrainEntity(trainDto);
        List<StationEntity> stations = new ArrayList<>();
        for (String station : trainDto.getStations()) {
            stations.add(stationDao.findByStationName(station));
        }
        trainEntity.setStationEntities(stations);
        trainGenericDao.save(trainEntity);
    }

    /**
     * Checking exist train in database by number
     * @param trainNumber train's number
     * @return true or false
     */
    @Override
    public boolean isAlreadyExist(Integer trainNumber) throws RailroadDaoException {
        if(trainGenericDao.getCountTrains(trainNumber) > 0){
           return true;
        }
        return false;
    }

    /**
     * Getting list data transfer objects of all trains
     * @return List of TrainDto
     */
    @Override
    @Transactional
    public List<TrainDto> getAll() throws RailroadDaoException {
        return trainDtoMapper.trainEntitiesToTrainDto(trainGenericDao.getAll());
    }


    /**
     * Finding train by number
     * @param trainNumber train's number
     * @return TrainEntity from database
     */
    @Override
    public TrainEntity findTrainByNumber(Integer trainNumber) throws RailroadDaoException {
        return trainGenericDao.findTrainByNumber(trainNumber);
    }

    /**
     * Getting train data transfer object by number
     * @param trainNumber train's number
     * @return TrainDto
     */
    @Transactional
    @Override
    public TrainDto getTrainDtoByNumber(Integer trainNumber) throws RailroadDaoException {
        return trainDtoMapper.trainEntityToTrainDto(trainGenericDao.findTrainByNumber(trainNumber));
    }

    /**
     * Getting all trains number from database
     * @return List of Integer
     */
    @Override
    public List<Integer> getAllTrainsNumbers() throws RailroadDaoException {
        return trainGenericDao.getAllTrainsNumbers();
    }
}
