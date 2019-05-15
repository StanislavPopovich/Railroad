package com.railroad.service.api;

import com.railroad.dto.way.WayDto;
import com.railroad.entity.WayEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;


/**
 * @author Stanislav Popovich
 */

public interface WayService {

    void save(WayDto wayDto) throws RailroadDaoException;

    List<WayEntity> getAll() throws RailroadDaoException;

    boolean isAlreadyExist(WayDto wayDto) throws RailroadDaoException;

}
