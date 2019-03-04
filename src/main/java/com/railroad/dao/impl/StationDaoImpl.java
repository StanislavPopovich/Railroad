package com.railroad.dao.impl;

import com.railroad.dao.StationDao;
import com.railroad.model.Station;
import org.springframework.stereotype.Repository;

@Repository
public class StationDaoImpl extends BaseDao<Station, Long> implements StationDao {
    public StationDaoImpl() {
        super(Station.class);
    }
}
