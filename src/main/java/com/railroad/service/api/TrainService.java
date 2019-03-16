package com.railroad.service.api;

import com.railroad.dto.TrainDto;

import java.util.List;

public interface TrainService {
    void save(TrainDto trainDto);
    List<TrainDto> getAll();
}
