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

import java.util.*;

@Service
public class TrainServiceImpl implements TrainService {


    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private StationGenericDao stationDao;

    @Autowired
    private TrainEntityDtoMapper trainDtoMapper;

    @Autowired
    private WayService wayService;

    //Надо переписать!!!!!
    @Override
    public void save(TrainDto trainDto) {
        LinkedList<String> trainStations = new LinkedList<>();
        String[] stationsArr = trainDto.getStations().getFirst().split("=>");
        for(int i = 0; i < stationsArr.length; i++){
            trainStations.add(stationsArr[i]);
        }
        trainDto.setStations(trainStations);
        TrainEntity trainEntity = trainDtoMapper.trainDtoToTrainEntity(trainDto);
        List<StationEntity> stations = new ArrayList<>();
        for(StationEntity stationEntity: trainEntity.getStationEntities()){
            stations.add(stationDao.findByStationName(stationEntity.getName()));
        }
        trainEntity.setStationEntities(stations);
        trainGenericDao.save(trainEntity);
    }

    /**
     * The method returns all trains from db
     * @return
     */
    @Override
    public List<TrainDto> getAll() {
        return trainDtoMapper.trainEntitiesToTrainDto(trainGenericDao.getAll());
    }

    /**
     * The method returns all direct trains which passing through by start station and destination station
     * @param startStation
     * @param destStation
     * @return
     */
    @Override
    public List<TrainDto> getDirectTrains(String startStation, String destStation) {
        return getTrainsByName(startStation, destStation);
    }

    /**
     * The method returns a pairs of trains that allow you to get from start station to destination station
     * @param startStation
     * @param destStation
     * @return set pairs of trains
     */
    @Override
    public Set<List<TrainDto>> getTrainsWithOneTransfer(String startStation, String destStation) {
        List<String> routes = wayService.getAllRoutes(startStation, destStation);
        Set<List<TrainDto>> allTrainsWithOneTransfer = new HashSet<>();
        for(String route: routes){
            String[] stationsId = route.split(",");
            if(stationsId.length != 2){
                for(int i = 0; i < stationsId.length - 1; i++){
                    if(i != stationsId.length - 1){
                        List<TrainDto> firstTrains = getTrainsWithoutDestStation(new Long(stationsId[i]),
                                new Long(stationsId[i]) + 1L, destStation);
                        if(firstTrains.size() != 0){
                            for(TrainDto trainDto: firstTrains){
                                for(TrainDto secondTrainDto: getNextTrains(trainDto, destStation)){
                                    if(secondTrainDto != null){
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
    }

    /**
     * The method returns a trains which allow you get to destination station after first train
     * @param train
     * @param destStation
     * @return list of trains
     */
    private List<TrainDto> getNextTrains(TrainDto train, String destStation){
        return getDirectTrains(train.getStations().getLast(), destStation);
    }

    /**
     * The method returns one pair of trains which allow you get from start station to destination station
     * @param firstTrain
     * @param secondTrain
     * @return list which having two trains
     */
    private List<TrainDto> getVariantTrainsWithOneTransfer(TrainDto firstTrain, TrainDto secondTrain){
        List<TrainDto> targetTrains = new ArrayList<>();
        targetTrains.add(firstTrain);
        targetTrains.add(secondTrain);
        return targetTrains;
    }

    /**
     * The method returns trains that contain the path passing through start station and destination station
     * @param startStationId
     * @param endStationId
     * @return list of trains
     */
    private List<TrainDto> getTrainsByStationsId(Long startStationId, Long endStationId){
        return getTrainsByName(stationDao.getById(startStationId).getName(), stationDao.getById(endStationId).getName());
    }

    /**
     * The method returns all trains which passing through by start station
     * @param startStationId
     * @param endStationId
     * @param destStation
     * @return list of trains
     */
    private List<TrainDto> getTrainsWithoutDestStation(Long startStationId, Long endStationId, String destStation){
        List<TrainDto> allTrains = getTrainsByStationsId(startStationId, endStationId);
        List<TrainDto> resTrains = new ArrayList<>();
        for(TrainDto trainDto: allTrains){
            if(!trainDto.getStations().contains(destStation)){
                resTrains.add(trainDto);
            }
        }
        return resTrains;
    }

    /**
     * The method returns all trains which passing through by start station and destination station
     * @param startStation
     * @param destStation
     * @return list of trains
     */
    private List<TrainDto> getTrainsByName(String startStation, String destStation){
        List<TrainDto> trains = trainDtoMapper.trainEntitiesToTrainDto(trainGenericDao.
                getTrainsByStations(stationDao.findByStationName(startStation).getId(),
                        stationDao.findByStationName(destStation).getId()));
        List<TrainDto> resultTrains = new ArrayList<>();
        for(TrainDto trainDto: trains){
            if(trainDto.getStations().indexOf(startStation) < trainDto.getStations().indexOf(destStation)){
                resultTrains.add(trainDto);
            }
        }
        return resultTrains;
    }

}
