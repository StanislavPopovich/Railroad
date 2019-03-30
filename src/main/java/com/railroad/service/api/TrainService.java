package com.railroad.service.api;

import com.railroad.dto.TrainDto;

import java.util.List;
import java.util.Set;

public interface TrainService {
    void save(TrainDto trainDto);
    List<TrainDto> getAll();
    List<TrainDto> getDirectTrains(String startStation, String destStation);
    Set<List<TrainDto>> getTrainsWithOneTransfer(String startStation, String destStation);
}
