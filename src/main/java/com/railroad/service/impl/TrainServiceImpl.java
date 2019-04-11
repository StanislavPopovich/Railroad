package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.TrainDto;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.TrainService;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TrainServiceImpl implements TrainService {


    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private StationGenericDao stationDao;

    @Autowired
    private TrainEntityDtoMapper trainDtoMapper;


    /**
     * The method send train in dao layer
     * @param trainDto
     */
    // +
    @Override
    @Transactional
    public void save(TrainDto trainDto) {
        LinkedList<String> trainStations = new LinkedList<>();
        String[] stationsArr = trainDto.getStations().getFirst().split("=>");
        for (int i = 0; i < stationsArr.length; i++) {
            trainStations.add(stationsArr[i]);
        }
        trainDto.setStations(trainStations);
        TrainEntity trainEntity = trainDtoMapper.trainDtoToTrainEntity(trainDto);
        List<StationEntity> stations = new ArrayList<>();
        for (StationEntity stationEntity : trainEntity.getStationEntities()) {
            stations.add(stationDao.findByStationName(stationEntity.getName()));
        }
        trainEntity.setStationEntities(stations);
        trainGenericDao.save(trainEntity);
    }

    /**
     * The method returns all trains
     *
     * @return
     */
    @Override
    @Transactional
    //+
    public List<TrainDto> getAll() {
        return trainDtoMapper.trainEntitiesToTrainDto(trainGenericDao.getAll());
    }

    @Override
    //+
    public TrainEntity findTrainEntityByNumber(Integer trainNumber) {
        return trainGenericDao.findTrainByNumber(trainNumber);
    }




















        /*public Set<List<TrainDto>> getTrainsWithOneTransfer(String startStation, String destStation) {
        List<String> routes = wayService.getAllRoutes(startStation, destStation);
        Set<List<TrainDto>> allTrainsWithOneTransfer = new HashSet<>();
        for (String route : routes) {
            String[] stationsId = route.split(",");
            if (stationsId.length != 2) {
                for (int i = 0; i < stationsId.length - 1; i++) {
                    if (i != stationsId.length - 1) {
                        List<TrainDto> firstTrains = getTrainsWithoutDestStation(new Long(stationsId[i]),
                                new Long(stationsId[i]) + 1L, destStation);
                        if (firstTrains.size() != 0) {
                            for (TrainDto trainDto : firstTrains) {
                                for (TrainDto secondTrainDto : getNextTrains(trainDto, destStation)) {
                                    if (secondTrainDto != null) {
                                        allTrainsWithOneTransfer.add(getVariantTrainsWithOneTransfer(trainDto, secondTrainDto));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return allTrainsWithOneTransfer;
    }*/

    /*private List<TrainDto> getVariantTrainsWithOneTransfer(TrainDto firstTrain, TrainDto secondTrain) {
        List<TrainDto> targetTrains = new ArrayList<>();
        targetTrains.add(firstTrain);
        targetTrains.add(secondTrain);
        return targetTrains;
    }*/

    /**
     * The method returns trains that contain the path passing through start station and destination station
     *
     * @param startStationId
     * @param endStationId
     * @return list of trains
     */
   /* private List<TrainDto> getTrainsByStationsId(Long startStationId, Long endStationId) {
        return getTrainsByName(stationDao.getById(startStationId).getName(), stationDao.getById(endStationId).getName());
    }*/

    /**
     * The method returns all trains which passing through by start station
     *
     * @param startStationId
     * @param endStationId
     * @param destStation
     * @return list of trains
     */
    /*private List<TrainDto> getTrainsWithoutDestStation(Long startStationId, Long endStationId, String destStation) {
        List<TrainDto> allTrains = getTrainsByStationsId(startStationId, endStationId);
        List<TrainDto> resTrains = new ArrayList<>();
        for (TrainDto trainDto : allTrains) {
            if (!trainDto.getStations().contains(destStation)) {
                resTrains.add(trainDto);
            }
        }
        return resTrains;
    }*/

    /**
     * The method returns all trains which passing through by start station and destination station
     *
     * @param startStation
     * @param destStation
     * @return list of trains
     */
    /*private List<TrainDto> getTrainsByName(String startStation, String destStation) {
        *//*Long startStationId = stationDao.findByStationName(startStation).getId();
        Long destStationId = stationDao.findByStationName(destStation).getId();*//*
        StationEntity stationEntity = stationDao.findByStationName(startStation);
        StationEntity startStation2 = stationDao.findByStationName(destStation);
        List<TrainDto> trains = trainDtoMapper.trainEntitiesToTrainDto(trainGenericDao.
                getTrainsByStations(stationEntity, startStation2));
        List<TrainDto> resultTrains = new ArrayList<>();
        for (TrainDto trainDto : trains) {
            if (trainDto.getStations().indexOf(startStation) < trainDto.getStations().indexOf(destStation)) {
                resultTrains.add(trainDto);
            }
        }
        return resultTrains;
    }*/

}
