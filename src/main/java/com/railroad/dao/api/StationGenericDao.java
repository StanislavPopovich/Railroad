package com.railroad.dao.api;

import com.railroad.entity.StationEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;

/**
 * DAO for the {@link StationEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface StationGenericDao extends GenericDao<StationEntity, Long> {

    StationEntity findByStationName(String name) throws RailroadDaoException;

    List<String> getAllStationNames() throws RailroadDaoException;

    int getIdOfLastStation() throws RailroadDaoException;

    Long getCountStations(String stationName) throws RailroadDaoException;
}
