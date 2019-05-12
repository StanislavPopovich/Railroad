package com.railroad.service.api;

import com.railroad.dto.way.WayDto;
import com.railroad.entity.WayEntity;

import java.util.List;

public interface WayService {
    void save(WayDto wayDto);
    List<WayDto> getAllWayDtos();
    List<WayEntity> getAll();
    boolean isAlreadyExist(WayDto wayDto);
}
