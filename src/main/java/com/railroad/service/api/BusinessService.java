package com.railroad.service.api;
import com.railroad.dto.StationDto;
import com.railroad.dto.TrainDto;

import java.util.List;

public interface BusinessService {
    void saveTrain(TrainDto trainDto);
    List<String> getAllStations();
    List<TrainDto> getAllTrains();
    List<List<String>> getAllRoutes(String startStation, String endStation);

}
