package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.model.Way;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WayGenericDaoImpl extends BaseGenericDao<Way, Long> implements WayGenericDao {

    public WayGenericDaoImpl() {
        super(Way.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Way> findByStationId(Long stationId) {
        Session session = getSession();
        return session.createQuery("from Way p where p.firstStation=:firstStation").
                setParameter("firstStation", stationId).list();
    }
}
