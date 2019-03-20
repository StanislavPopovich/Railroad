package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.model.Way;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO implementation for the {@link com.railroad.model.Way} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class WayGenericDaoImpl extends BaseGenericDao<Way, Long> implements WayGenericDao {

    public WayGenericDaoImpl() {
        super(Way.class);
    }

    /**
     *Method for finding Ways in DB
     *
     * @param stationId
     * @return List of ways with selected station id
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Way> findByStationId(Long stationId) {
        Session session = getSession();
        return session.createQuery("from Way p where p.firstStation=:firstStation").
                setParameter("firstStation", stationId).list();
    }
}
