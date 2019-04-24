package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.WayGenericDao;
import com.railroad.dto.WayDto;
import com.railroad.mapper.WayEntityDtoMapper;
import com.railroad.model.WayEntity;
import com.railroad.service.api.WayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WayServiceImpl implements WayService {

    private static final Logger logger = Logger.getLogger(WayServiceImpl.class);

    @Autowired
    private WayGenericDao wayGenericDao;

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private WayEntityDtoMapper wayDtoMapper;



    @Override
    @Transactional
    public void save(WayDto wayDto) {
        WayEntity wayEntity = wayDtoMapper.wayDtoToWayEntity(wayDto);
        wayEntity.setFirstStationEntity(stationGenericDao.findByStationName(wayDto.getFirstStation()));
        wayEntity.setSecondStationEntity(stationGenericDao.findByStationName(wayDto.getSecondStation()));
        wayGenericDao.save(wayEntity);

    }

    @Override
    @Transactional
    public List<WayDto> getAllWayDtos() {
        return wayDtoMapper.wayEntitiesToWayDtos(wayGenericDao.getAll());
    }

    @Override
    @Transactional
    public List<WayEntity> getAll() {
        return wayGenericDao.getAll();
    }

}
