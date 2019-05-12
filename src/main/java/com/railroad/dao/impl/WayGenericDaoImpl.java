package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.entity.WayEntity;
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


    @Override
    public Long getCountWay(WayEntity wayEntity) {
        Long count = (Long) entityManager.createQuery("select count(w) from " +
                "WayEntity w where w.firstStationEntity= :firstStation and w.secondStationEntity= :secondStation")
                .setParameter("firstStation", wayEntity.getFirstStationEntity()).
                        setParameter("secondStation", wayEntity.getSecondStationEntity()).
                getSingleResult();
        return count;
    }
}
