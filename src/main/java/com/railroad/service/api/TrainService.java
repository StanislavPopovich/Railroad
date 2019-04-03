package com.railroad.service.api;

import com.railroad.dto.TrainDto;

import java.util.List;

/**
 * Service interface for {@link com.railroad.model.TrainEntity}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface TrainService {

    /**
     * The method sends trainDto to dao layer
     * @param trainDto
     */
    void save(TrainDto trainDto);

    /**
     * The method returns all trainDtos from dao layer
     * @return list of TrainDto
     */
    List<TrainDto> getAll();
}
