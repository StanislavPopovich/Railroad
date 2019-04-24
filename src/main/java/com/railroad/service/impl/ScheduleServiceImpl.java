package com.railroad.service.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.mapper.ScheduleInfoDtoMapper;
import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.ScheduleDto;
import com.railroad.dto.ScheduleInfoDto;
import com.railroad.dto.ScheduleUpdateDto;
import com.railroad.mapper.ScheduleEntityDtoMapper;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ScheduleInfoDtoMapper scheduleInfoDtoMapper;

    @Transactional
    @Override
    //schedule controller
    public void save(ScheduleDto scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleMapper.scheduleDtoToScheduleEntity(scheduleDto);
        scheduleEntity.setStationEntity(stationDao.findByStationName(scheduleDto.getStationName()));
        scheduleEntity.setTrainEntity(trainDao.findTrainByNumber(scheduleDto.getTrainNumber()));
        scheduleDao.save(scheduleEntity);
    }

    @Override
    public List<ScheduleEntity> getSchedulesByStationNameAndDepartDate(String stationName, Date departDate) {
        StationEntity stationEntity = stationDao.findByStationName(stationName);
        List<ScheduleEntity> schedules = scheduleDao.findScheduleByStationAndDepartDate(stationEntity, departDate);
        return schedules;
    }

    @Override
    @Transactional
    public List<ScheduleInfoDto> getScheduleDtosByStationNameAndDepartDate(String stationName, Date date) {
        return scheduleInfoDtoMapper.scheduleEntitieToScheduleInfoDtos(getSchedulesByStationNameAndDepartDate(stationName, date));
    }


    @Override
    public List<ScheduleDto> getAll(){
        return scheduleMapper.scheduleEntitiesToScheduleDtos(scheduleDao.getAll());
    }

    @Override
    public ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        return scheduleDao.findScheduleByTrainAndDepartDate(trainEntity, departDate);
    }

    @Override
    public ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate) {
        return scheduleDao.findScheduleByTrainAndArrivalDate(trainEntity, arrivalDate);
    }

    @Override
    public List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDateFromFirstStation) {
        return scheduleDao.findSchedulesForTrain(trainEntity,departDateFromFirstStation);
    }

    @Override
    public List<Date> getDepartDatesForTrain(TrainEntity trainEntity) {
        return scheduleDao.getDepartDatesForTrain(trainEntity);
    }

    @Override
    public void removeSchedulesByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        scheduleDao.removeScheduleByTrainAndDepartDate(trainEntity, departDate);
    }

    @Override
    public void updateSchedule(ScheduleEntity scheduleEntity) {
        scheduleDao.update(scheduleEntity);
    }

    @Override
    public ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity, Date departDate) {
        return scheduleDao.getScheduleBuTrainAndStationAndDate(trainEntity, stationEntity, departDate);
    }

    @Override
    public List<ScheduleUpdateDto> getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        return scheduleMapper.scheduleEntityToScheduleUpdateDto(findSchedulesForTrain(trainEntity, departDate));
    }
}
