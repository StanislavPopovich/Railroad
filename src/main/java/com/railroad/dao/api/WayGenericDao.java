package com.railroad.dao.api;

import com.railroad.model.WayEntity;

import java.util.List;

/**
 * DAO for the {@link WayEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface WayGenericDao extends GenericDao<WayEntity, Long> {
        /**
         *Method for finding Ways in DB
         *
         * @param id
         * @return List of ways with selected station id
         */
        List<WayEntity> findByStationId(Long id);
}
