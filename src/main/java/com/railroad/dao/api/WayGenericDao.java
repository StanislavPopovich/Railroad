package com.railroad.dao.api;

import com.railroad.entity.WayEntity;
import java.util.List;

/**
 * DAO for the {@link WayEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface WayGenericDao extends GenericDao<WayEntity, Long> {

        Long getCountWay(WayEntity wayEntity);
}
