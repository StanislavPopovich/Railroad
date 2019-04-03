package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.model.WayEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO implementation for the {@link WayEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class WayGenericDaoImpl extends BaseGenericDao<WayEntity, Long> implements WayGenericDao {

    public WayGenericDaoImpl() {
        super(WayEntity.class);
    }

    /**
     *Method for finding Ways in DB
     *
     * @param stationId
     * @return List of ways with selected station id
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WayEntity> findByStationId(Long stationId) {
        Session session = getSession();
        List<WayEntity> ways = session.createQuery("select w from WayEntity w where w.firstStation=:firstStation").
                setParameter("firstStation", stationId).list();
        session.close();
        return ways;
    }
}
