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
         * Returns entities that matches selected station
         *
         * @param id
         * @return List ways with selected station id
         */

        //TODO
        List<WayEntity> findByStationId(Long id);
}
