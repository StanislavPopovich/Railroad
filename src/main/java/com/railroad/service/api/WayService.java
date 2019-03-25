package com.railroad.service.api;

import com.railroad.dto.WayDto;

import java.util.List;

public interface WayService {
    void save(WayDto wayDto);
    List<WayDto> getAll();
    List<String> getAllRoutes(String startStation, String endStation);
}
