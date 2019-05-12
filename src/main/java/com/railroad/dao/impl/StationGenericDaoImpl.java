package com.railroad.dao.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
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

    @Override
    public StationEntity findByStationName(String name) {
        StationEntity stationEntity = (StationEntity) entityManager.createQuery("select s from StationEntity s " +
                "where s.name= :name").
                setParameter("name", name).getSingleResult();
        return stationEntity;
    }

    @Override
    public List<String> getAllStationNames() {
        List<String> stationNames = entityManager.createQuery("select s.name from StationEntity s " +
                "order by s.name").getResultList();
        return stationNames;
    }

    @Override
    public int getIdOfLastStation() {
        StationEntity stationEntity = (StationEntity)entityManager.
                createQuery("select s from StationEntity s order by s.id desc ").setMaxResults(1).getSingleResult();
        return stationEntity.getId().intValue();
    }

    @Override
    public Long getCountStations(String stationName) {
        Long count = (Long) entityManager.createQuery("select count(s) from " +
                "StationEntity s where s.name= :name").setParameter("name", stationName).
                getSingleResult();
        return count;
    }
}
