package com.railroad.service.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.ScheduleDto;
import com.railroad.mapper.ScheduleEntityDtoMapper;
import com.railroad.model.ScheduleEntity;
import com.railroad.service.api.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger logger = Logger.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private ScheduleGenericDao scheduleDao;
    @Autowired
    private ScheduleEntityDtoMapper scheduleMapper;
    @Autowired
    private StationGenericDao stationDao;
    @Autowired
    private TrainGenericDao trainDao;

    @Override
    public void save(ScheduleDto scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleMapper.scheduleDtoToScheduleEntity(scheduleDto);
        scheduleEntity.setStationEntity(stationDao.findByStationName(scheduleDto.getStationName()));
        scheduleEntity.setTrainEntity(trainDao.findTrainByNumber(scheduleDto.getTrainNumber()));
        logger.info(scheduleEntity.getDate());
        logger.info(scheduleEntity.getStationEntity().getName());
        logger.info(scheduleEntity.getTrainEntity().getNumber());
        scheduleDao.save(scheduleEntity);
    }

    @Override
    public List<ScheduleDto> getScheduleByStationName(String stationName) {
        List<ScheduleEntity> scheduleEntities = scheduleDao.
                findScheduleByStationId(stationDao.findByStationName(stationName).getId());
        return scheduleMapper.scheduleEntitiesToScheduleDtos(scheduleEntities);
    }

    @Override
    public List<ScheduleDto> getScheduleByTrainNumber(Integer trainNumber) {
        List<ScheduleEntity> scheduleEntities = scheduleDao.
                findScheduleByStationId(trainDao.findTrainByNumber(trainNumber).getId());
        return scheduleMapper.scheduleEntitiesToScheduleDtos(scheduleEntities);
    }
}
