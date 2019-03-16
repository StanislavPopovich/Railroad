package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.TrainDto;
import com.railroad.model.Station;
import com.railroad.model.Train;
import com.railroad.service.api.TrainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrainServiceImpl implements TrainService {


    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private StationGenericDao stationGenericDao;

    @Override
    public void save(TrainDto trainDto) {
        Train train = new Train();
        train.setNumber(trainDto.getNumber());
        train.setStartStationId(stationGenericDao.findByStationName(trainDto.getStartStation()).getId());
        train.setEndStationId(stationGenericDao.findByStationName(trainDto.getEndStation()).getId());
        train.setSeats(trainDto.getSeats());
        Set<Station> stations = new HashSet<>();
        for(String stationName: trainDto.getStations()){
            Station station =stationGenericDao.findByStationName(stationName);
            stations.add(station);
        }
        train.setStations(stations);
        trainGenericDao.save(train);
    }

    @Override
    public List<TrainDto> getAll() {
        List<Train> trains = trainGenericDao.getAll();
        List<TrainDto> trainDtos = new ArrayList<>();
        if(!trains.isEmpty()){
            for(Train train: trains){
                TrainDto trainDto = new TrainDto();
                trainDto.setNumber(train.getNumber());
                trainDto.setStartStation(stationGenericDao.getById(train.getStartStationId()).getName());
                trainDto.setEndStation(stationGenericDao.getById(train.getEndStationId()).getName());
                trainDto.setSeats(train.getSeats());
                List<String> stationNames = new ArrayList<>();
                for(Station station: train.getStations()){
                    stationNames.add(station.getName());
                }
                trainDto.setStations(stationNames);
                trainDtos.add(trainDto);
            }

        }else{
            // exception
        }
        return trainDtos;
    }
}
