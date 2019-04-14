package com.railroad.dao.api;

import com.railroad.model.StationEntity;

import java.util.List;

/**
 * DAO for the {@link StationEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface StationGenericDao extends GenericDao<StationEntity, Long> {

    /**
     *Method for finding StationEntity in DB
     *
     * @param name
     * @return StationEntity with selected name
     */
    StationEntity findByStationName(String name);
    List<String> getAllStationNames();
    int getIdOfLastStation();
}
