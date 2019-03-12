package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.model.Station;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class StationGenericDaoImpl extends BaseGenericDao<Station, Long> implements StationGenericDao {
    public StationGenericDaoImpl() {
        super(Station.class);
    }

    @Override
    public Station findByStationName(String name) {
        Session session = getSession();
        Station station = (Station) session.createQuery("from Station p where p.name=:name").
                setParameter("name", name).uniqueResult();
        return station;
    }
}
