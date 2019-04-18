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
     * Returns an entity from db by name
     *
     * @param name
     * @return StationEntity with selected name
     */
    StationEntity findByStationName(String name);

    /**
     * Returns all names of entities from db
     * @return list names of entities
     */
    List<String> getAllStationNames();

    /**
     * Returns last id of entity from db
     * @return id
     */
    int getIdOfLastStation();
}
