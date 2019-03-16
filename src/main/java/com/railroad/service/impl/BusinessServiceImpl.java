package com.railroad.service.impl;

import com.railroad.dto.StationDto;
import com.railroad.dto.TrainDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.TrainService;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private WayService wayService;


    @Transactional
    @Override
    public void saveTrain(TrainDto trainDto) {
        trainService.save(trainDto);
    }

    @Override
    public List<String> getAllStations() {
        return stationService.getAll();
    }

    @Transactional
    @Override
    public List<TrainDto> getAllTrains() {
        return trainService.getAll();
    }

    @Transactional
    @Override
    public List<List<String>> getAllRoutes(String startStation, String endStation) {
        List<List<String>> targetRoutes = new ArrayList<>();
        List<String> routes = wayService.getAllRoutes(startStation, endStation);
        for(String str: routes){
            List<String> route = new ArrayList<>();
            String[] stationsId = str.split(",");
            for(String stationId: stationsId){
                route.add(stationService.getStationById(new Long(stationId)).getName());
            }
            targetRoutes.add(route);
        }
        return targetRoutes;
    }
}
