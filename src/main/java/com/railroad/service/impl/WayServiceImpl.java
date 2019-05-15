package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.WayGenericDao;
import com.railroad.dto.way.WayDto;
import com.railroad.entity.StationEntity;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.WayEntityDtoMapper;
import com.railroad.entity.WayEntity;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class WayServiceImpl implements WayService {

    @Autowired
    private WayGenericDao wayGenericDao;

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private WayEntityDtoMapper wayDtoMapper;


    /**
     * Saving way to db
     * @param wayDto way
     */
    @Override
    @Transactional
    public void save(WayDto wayDto) throws RailroadDaoException {
        WayEntity wayEntity = wayDtoMapper.wayDtoToWayEntity(wayDto);
        wayEntity.setFirstStationEntity(stationGenericDao.findByStationName(wayDto.getFirstStation()));
        wayEntity.setSecondStationEntity(stationGenericDao.findByStationName(wayDto.getSecondStation()));
        wayGenericDao.save(wayEntity);

    }

    /**
     * Getting All ways from db
     * @return List of WayEntity
     */
    @Override
    @Transactional
    public List<WayEntity> getAll() throws RailroadDaoException {
        return wayGenericDao.getAll();
    }

    /**
     * Checking that way already exist in db
     * @param wayDto way
     * @return boolean
     */
    @Transactional
    @Override
    public boolean isAlreadyExist(WayDto wayDto) throws RailroadDaoException {
        StationEntity firstStation = stationGenericDao.findByStationName(wayDto.getFirstStation());
        StationEntity secondStation = stationGenericDao.findByStationName(wayDto.getSecondStation());
        WayEntity way = wayDtoMapper.wayDtoToWayEntity(wayDto);
        way.setFirstStationEntity(firstStation);
        way.setSecondStationEntity(secondStation);
        if(wayGenericDao.getCountWay(way) > 0){
            return true;
        }
        return false;
    }

}
