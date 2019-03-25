package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.model.StationEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link StationEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class StationGenericDaoImpl extends BaseGenericDao<StationEntity, Long> implements StationGenericDao {
    public StationGenericDaoImpl() {
        super(StationEntity.class);
    }

    /**
     *Method for finding StationEntity in DB
     *
     * @param name
     * @return StationEntity with selected name
     */
    @Override
    public StationEntity findByStationName(String name) {
        Session session = getSession();
        StationEntity stationEntity = (StationEntity) session.createQuery("select s from StationEntity s where s.name=:name").
                setParameter("name", name).uniqueResult();
        return stationEntity;
    }
}
