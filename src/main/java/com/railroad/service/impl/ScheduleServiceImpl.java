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
        scheduleDao.save(scheduleEntity);
    }

    @Override
    public List<ScheduleEntity> getScheduleByStationNameAndDepartDate(String stationName, Date departDate) {
        Long stationId = stationDao.findByStationName(stationName).getId();
        List<ScheduleEntity> schedules = scheduleDao.findScheduleByStationIdAndDepartDate(stationId, departDate);
        return schedules;
    }

    @Override
    public List<ScheduleEntity> findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        Date arrivalDate = getArrivalDate(trainEntity.getTimeWay(), departDate);
        return scheduleDao.findScheduleByTrainAndDates(trainEntity, departDate,arrivalDate);
    }


    @Override
    public List<ScheduleDto> getAll(){
        return scheduleMapper.scheduleEntitiesToScheduleDtos(scheduleDao.getAll());
    }

    private Date getArrivalDate(Date trainTimeWay, Date departDate){
        DateTime dateTime = new DateTime(departDate);
        DateTime timeWay = new DateTime(trainTimeWay, DateTimeZone.UTC);
        DateTime arrivalDate = dateTime.plusHours(timeWay.getHourOfDay()).plusMinutes(timeWay.getMinuteOfHour());
        return arrivalDate.toDate();
    }
}
