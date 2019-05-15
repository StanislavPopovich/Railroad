package com.railroad.dao.impl;

import com.railroad.dao.api.WayGenericDao;
import com.railroad.entity.StationEntity;
import com.railroad.entity.WayEntity;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
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

    private static final org.apache.log4j.Logger logger = Logger.getLogger(WayGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public WayGenericDaoImpl() {
        super(WayEntity.class);
    }

    /**
     * Getting 1 if way is exist in database
     * @param wayEntity way
     * @return
     */
    @Override
    public Long getCountWay(WayEntity wayEntity) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(w) from " +
                    "WayEntity w where w.firstStationEntity= :firstStation and w.secondStationEntity= :secondStation")
                    .setParameter("firstStation", wayEntity.getFirstStationEntity()).
                            setParameter("secondStation", wayEntity.getSecondStationEntity()).
                            getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in WayGenericDaoImpl - getCountWay().");
            throw new RailroadDaoException(e);
        }
        return count;
    }
}
