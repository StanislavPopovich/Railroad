package com.railroad.dao.api;

import com.railroad.model.Way;

import java.util.List;

/**
 * DAO for the {@link com.railroad.model.Way} objects.
 *
 * @author Stanislav Popovich
 */
public interface WayGenericDao extends GenericDao<Way, Long> {
        /**
         *Method for finding Ways in DB
         *
         * @param id
         * @return List of ways with selected station id
         */
        List<Way> findByStationId(Long id);
}
