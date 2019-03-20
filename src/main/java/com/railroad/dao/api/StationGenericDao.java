package com.railroad.dao.api;

import com.railroad.model.Station;

/**
 * DAO for the {@link com.railroad.model.Station} objects.
 *
 * @author Stanislav Popovich
 */
public interface StationGenericDao extends GenericDao<Station, Long> {

    /**
     *Method for finding Station in DB
     *
     * @param name
     * @return Station with selected name
     */
    Station findByStationName(String name);
}
