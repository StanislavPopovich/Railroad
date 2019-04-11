package com.railroad.service.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.ScheduleDto;
import com.railroad.mapper.ScheduleEntityDtoMapper;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.ScheduleService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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

    @Transactional
    @Override
    //+
    public void save(ScheduleDto scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleMapper.scheduleDtoToScheduleEntity(scheduleDto);
        scheduleEntity.setStationEntity(stationDao.findByStationName(scheduleDto.getStationName()));
        scheduleEntity.setTrainEntity(trainDao.findTrainByNumber(scheduleDto.getTrainNumber()));
        scheduleDao.save(scheduleEntity);
    }

    @Override
    //+ Надо заменить id
    public List<ScheduleEntity> getSchedulesByStationNameAndDepartDate(String stationName, Date departDate) {
        Long stationId = stationDao.findByStationName(stationName).getId();
        List<ScheduleEntity> schedules = scheduleDao.findScheduleByStationIdAndDepartDate(stationId, departDate);
        return schedules;
    }



    @Override
    public List<ScheduleDto> getAll(){
        return scheduleMapper.scheduleEntitiesToScheduleDtos(scheduleDao.getAll());
    }

    @Override
    //+
    public ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        return scheduleDao.findScheduleByTrainAndDepartDate(trainEntity, departDate);
    }

    @Override
    //+
    public ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate) {
        return scheduleDao.findScheduleByTrainAndArrivalDate(trainEntity, arrivalDate);
    }

    @Override
    //+
    public List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity, Date departDate) {
        Date arrivalDate = getArrivalDate(trainEntity.getTimeWay(), departDate);
        Date newDepartDate = getDepartDate(trainEntity.getTimeWay(), departDate);
        return scheduleDao.findSchedulesForTrain(trainEntity,newDepartDate,arrivalDate);
    }

    private Date getArrivalDate(Date trainTimeWay, Date departDate){
        DateTime dateTime = new DateTime(departDate);
        DateTime timeWay = new DateTime(trainTimeWay, DateTimeZone.UTC);
        DateTime arrivalDate = dateTime.plusHours(timeWay.getHourOfDay()).plusMinutes(timeWay.getMinuteOfHour()).plusMinutes(30);
        return arrivalDate.toDate();
    }

    private Date getDepartDate(Date trainTimeWay, Date departDate){
        DateTime dateTime = new DateTime(departDate);
        DateTime timeWay = new DateTime(trainTimeWay, DateTimeZone.UTC);
        DateTime newDepartDate = dateTime.minusHours(timeWay.getHourOfDay()).minusMinutes(timeWay.getMinuteOfHour());
        return newDepartDate.toDate();
    }




}
