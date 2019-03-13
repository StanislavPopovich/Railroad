package com.railroad.service.api;

import com.railroad.dto.WayDto;
import com.railroad.model.Way;

import java.util.List;

public interface WayService {
    void save(WayDto wayDto);
    List<WayDto> getAll();

}
