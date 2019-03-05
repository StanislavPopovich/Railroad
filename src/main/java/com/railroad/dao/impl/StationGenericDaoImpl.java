package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.model.Station;
import org.springframework.stereotype.Repository;

@Repository
public class StationGenericDaoImpl extends BaseGenericDao<Station, Long> implements StationGenericDao {
    public StationGenericDaoImpl() {
        super(Station.class);
    }
}
