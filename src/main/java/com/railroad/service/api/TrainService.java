package com.railroad.service.api;

import com.railroad.dto.train.TrainDto;
import com.railroad.entity.TrainEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;

/**
 * @author Stanislav Popovich
 */
public interface TrainService {

    void save(TrainDto trainDto) throws RailroadDaoException;

    List<TrainDto> getAll() throws RailroadDaoException;

    TrainEntity findTrainByNumber(Integer trainNumber) throws RailroadDaoException;

    TrainDto getTrainDtoByNumber(Integer trainNumber) throws RailroadDaoException;

    List<Integer> getAllTrainsNumbers() throws RailroadDaoException;

    boolean isAlreadyExist(Integer trainNumber) throws RailroadDaoException;
}
