package com.railroad.dao.api;

import com.railroad.model.Way;

import java.util.List;

public interface WayGenericDao extends GenericDao<Way, Long> {
        List<Way> findByStationId(Long id);
}
