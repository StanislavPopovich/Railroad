package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.model.StationEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * DAO implementation for the {@link StationEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class StationGenericDaoImpl extends BaseGenericDao<StationEntity, Long> implements StationGenericDao {

    @PersistenceContext
    private EntityManager entityManager;

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
        StationEntity stationEntity = (StationEntity) entityManager.createQuery("select s from StationEntity s where s.name=:name").
                setParameter("name", name).getSingleResult();
        return stationEntity;
    }

    @Override
    public List<String> getAllStationNames() {
        List<String> stationNames = entityManager.createQuery("select s.name from StationEntity s").getResultList();
        return stationNames;
    }
}
