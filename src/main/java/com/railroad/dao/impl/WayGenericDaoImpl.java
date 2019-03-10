package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.model.Way;
import org.springframework.stereotype.Repository;

@Repository
public class WayGenericDaoImpl extends BaseGenericDao<Way, Long> implements WayGenericDao {

    public WayGenericDaoImpl() {
        super(Way.class);
    }
}
