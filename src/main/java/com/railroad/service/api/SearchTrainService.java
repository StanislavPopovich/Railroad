package com.railroad.service.api;

import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTransferTargetDto;
import com.railroad.exceptions.RailroadDaoException;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

public interface SearchTrainService {

    List<TrainTargetDto> getDirectTrains(String departStation, String arrivalStation, Date departDate) throws RailroadDaoException;

    List<TrainTransferTargetDto> getTransferTrains(String departStation, String arrivalStation, Date departDate) throws RailroadDaoException;
}
