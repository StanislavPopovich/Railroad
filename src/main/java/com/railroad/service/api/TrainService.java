package com.railroad.service.api;

import com.railroad.dto.train.TrainDto;
import com.railroad.entity.TrainEntity;

import java.util.List;

/**
 * Service interface for {@link com.railroad.entity.TrainEntity}
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

    TrainEntity findTrainEntityByNumber(Integer trainNumber);

    TrainDto getTrainDtoByNumber(Integer trainNumber);

    List<Integer> getAllTrainsNumbers();

    boolean trainAlreadyExist(Integer trainNumber);
}
