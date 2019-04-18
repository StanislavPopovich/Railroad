package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.model.WayEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO implementation for the {@link WayEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class WayGenericDaoImpl extends BaseGenericDao<WayEntity, Long> implements WayGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

    public WayGenericDaoImpl() {
        super(WayEntity.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WayEntity> findByStationId(Long stationId) {
        List<WayEntity> ways = entityManager.createQuery("select w from WayEntity w where w.firstStation=:firstStation").
                setParameter("firstStation", stationId).getResultList();
        return ways;
    }
}
