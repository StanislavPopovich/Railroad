package com.railroad.service.api;

import com.railroad.exceptions.RailroadDaoException;

/**
 * @author Stanislav Popovich
 */

public interface TableService {

    void updateStations() throws RailroadDaoException;

    void updateSchedule() throws RailroadDaoException;
}
