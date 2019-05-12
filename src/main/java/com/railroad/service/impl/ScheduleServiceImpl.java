package com.railroad.service.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dto.schedule.*;
import com.railroad.mapper.ScheduleInfoDtoMapper;
import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.mapper.ScheduleEntityDtoMapper;
import com.railroad.mapper.ScheduleMessageInfoDtoMapper;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.service.api.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImpl extends BaseService implements ScheduleService {
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
    @Autowired
    private ScheduleMessageInfoDtoMapper scheduleMessageInfoDtoMapper;

    @Transactional
    @Override
    public void save(ScheduleTrainDto scheduleTrainDto) {
        List<ScheduleEntity> schedules = getNewSchedules(scheduleTrainDto);
        for(ScheduleEntity scheduleEntity: schedules){
            scheduleDao.save(scheduleEntity);
        }
    }

    @Override
    @Transactional
    public void updateSchedule(ScheduleUpdateDto scheduleUpdateDto) {
        List<ScheduleEntity> schedules = getChangedSchedules(scheduleUpdateDto);
        for(ScheduleEntity scheduleEntity: schedules){
            scheduleDao.update(scheduleEntity);
        }
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
    public ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity, Date departDate) {
        return scheduleDao.getScheduleBuTrainAndStationAndDate(trainEntity, stationEntity, departDate);
    }

    @Override
    public ScheduleUpdateDto getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        List<ScheduleEntity> schedules = findSchedulesForTrain(trainEntity, departDate);
        ScheduleUpdateDto scheduleUpdateDto = new ScheduleUpdateDto();
        List<String> stations = new ArrayList<>();
        List<String> arrivalDates = new ArrayList<>();
        List<String> departDates = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: schedules){
            stations.add(scheduleEntity.getStationEntity().getName());
            arrivalDates.add(format1.format(scheduleEntity.getArrivalDate()));
            departDates.add(format1.format(scheduleEntity.getDepartDate()));
        }
        scheduleUpdateDto.setNumber(trainEntity.getNumber());
        scheduleUpdateDto.setStations(stations);
        scheduleUpdateDto.setArrivalDates(arrivalDates);
        scheduleUpdateDto.setDepartDates(departDates);
        scheduleUpdateDto.setOldDepartDateFromFirstStation(format2.format(departDate));
        return scheduleUpdateDto;
    }

    @Override
    public List<ScheduleMessageInfoDto> getActualSchedule(Date currentDate) {
        return scheduleMessageInfoDtoMapper.scheduleEntitieToScheduleMessageInfoDtos(scheduleDao.getActualSchedules(currentDate));
    }

    public List<Integer> getTrainsNumberFromSchedule(){
        return scheduleDao.getTrainsNumberFromSchedule();
    }

    private List<ScheduleEntity> getNewSchedules(ScheduleTrainDto scheduleTrainDto){
        List<ScheduleEntity> newSchedules = new ArrayList<>();
        List<String> arrivalDates = scheduleTrainDto.getArrivalDates();
        List<String> departDates = scheduleTrainDto.getDepartDates();
        Date dateFromFirstStation = getDate(departDates.get(0), "yyyy-MM-dd");
        TrainEntity train = trainDao.findTrainByNumber(scheduleTrainDto.getNumber());
        List<String> stations = scheduleTrainDto.getStations();
        for(int i = 0; i < stations.size(); i++){
            StationEntity stationEntity = stationDao.findByStationName(stations.get(i));
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setTrainEntity(train);
            scheduleEntity.setStationEntity(stationEntity);
            scheduleEntity.setDepartDateFromFirstStation(dateFromFirstStation);
            scheduleEntity.setArrivalDate(getDate(arrivalDates.get(i), "yyyy-MM-dd HH:mm"));
            scheduleEntity.setDepartDate(getDate(departDates.get(i), "yyyy-MM-dd HH:mm"));
            newSchedules.add(scheduleEntity);
        }
        return newSchedules;
    }

    private List<ScheduleEntity> getChangedSchedules(ScheduleUpdateDto scheduleUpdateDto){
        List<ScheduleEntity> updatedSchedules = new ArrayList<>();
        List<String> arrivalDates = scheduleUpdateDto.getArrivalDates();
        List<String> departDates = scheduleUpdateDto.getDepartDates();
        Date dateFromFirstStation = getDate(departDates.get(0), "yyyy-MM-dd");
        TrainEntity train = trainDao.findTrainByNumber(scheduleUpdateDto.getNumber());
        List<String> stations = scheduleUpdateDto.getStations();
        for(int i = 0; i < stations.size(); i++){
            StationEntity stationEntity = stationDao.findByStationName(stations.get(i));
            ScheduleEntity scheduleEntity = getScheduleByTrainAndStationAndDate(train, stationEntity,
                    getDate(scheduleUpdateDto.getOldDepartDateFromFirstStation(), "yyyy-MM-dd"));
            scheduleEntity.setDepartDateFromFirstStation(dateFromFirstStation);
            scheduleEntity.setArrivalDate(getDate(arrivalDates.get(i), "yyyy-MM-dd HH:mm"));
            scheduleEntity.setDepartDate(getDate(departDates.get(i), "yyyy-MM-dd HH:mm"));
            updatedSchedules.add(scheduleEntity);
        }
        return updatedSchedules;
    }

}
