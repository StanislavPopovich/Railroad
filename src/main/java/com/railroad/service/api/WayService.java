package com.railroad.service.api;

import com.railroad.dto.way.WayDto;
import com.railroad.model.WayEntity;

import java.util.List;

public interface WayService {
    void save(WayDto wayDto);
    List<WayDto> getAllWayDtos();
    List<WayEntity> getAll();
    /*List<String> getAllRoutes(String startStation, String endStation);*/
}
