package com.railroad.service.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dto.schedule.*;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.ScheduleInfoDtoMapper;
import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.mapper.ScheduleEntityDtoMapper;
import com.railroad.mapper.ScheduleMessageInfoDtoMapper;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.service.api.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class ScheduleServiceImpl extends BaseService implements ScheduleService {

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

    /**
     * Saving a new entity to database
     * @param scheduleTrainDto schedule for all stations for selected train
     */
    @Transactional
    @Override
    public void save(ScheduleTrainDto scheduleTrainDto) throws RailroadDaoException {
        List<ScheduleEntity> schedules = getNewSchedules(scheduleTrainDto);
        for(ScheduleEntity scheduleEntity: schedules){
            scheduleDao.save(scheduleEntity);
        }
    }

    /**
     * Updating schedule for selected train
     * @param scheduleUpdateDto schedule for all stations for selected train
     */
    @Override
    @Transactional
    public void updateSchedule(ScheduleUpdateDto scheduleUpdateDto) throws RailroadDaoException {
        List<ScheduleEntity> schedules = getChangedSchedules(scheduleUpdateDto);
        for(ScheduleEntity scheduleEntity: schedules){
            scheduleDao.update(scheduleEntity);
        }
    }

    /**
     * Getting schedule for selected station and departure date
     * @param stationName station's name
     * @param departDate departure date
     * @return List of ScheduleEntities
     */
    @Override
    public List<ScheduleEntity> getScheduleByStationAndDepartDate(String stationName, Date departDate) throws RailroadDaoException {
        StationEntity stationEntity = stationDao.findByStationName(stationName);
        List<ScheduleEntity> schedules = scheduleDao.findScheduleByStationAndDepartDate(stationEntity, departDate);
        return schedules;
    }

    /**
     * Getting schedule for selected station and departure date
     * @param stationName station's name
     * @param date departure date
     * @return List of ScheduleInfoDto
     */
    @Override
    @Transactional
    public List<ScheduleInfoDto> getScheduleDtosByStationAndDepartDate(String stationName, Date date) throws RailroadDaoException {
        return scheduleInfoDtoMapper.scheduleEntitieToScheduleInfoDtos(getScheduleByStationAndDepartDate(stationName, date));
    }


    /**
     * Getting list data transfer objects of all schedules
     * @return List of ScheduleDto
     */
    @Override
    public List<ScheduleDto> getAll() throws RailroadDaoException {
        return scheduleMapper.scheduleEntitiesToScheduleDtos(scheduleDao.getAll());
    }

    /**
     * Finding schedule by selected train and departure date
     * @param trainEntity train
     * @param departDate departure date
     * @return ScheduleEntity
     */
    @Override
    public ScheduleEntity findScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        return scheduleDao.findScheduleByTrainAndDepartDate(trainEntity, departDate);
    }

    /**
     * Finding schedule by selected train and arrival date
     * @param trainEntity train
     * @param arrivalDate arrival date
     * @return ScheduleEntity
     */
    @Override
    public ScheduleEntity findScheduleByTrainAndArrivalDate(TrainEntity trainEntity, Date arrivalDate) throws RailroadDaoException {
        return scheduleDao.findScheduleByTrainAndArrivalDate(trainEntity, arrivalDate);
    }

    /**
     * Finding schedule for all stations of train route by departure date from first station
     * @param trainEntity train
     * @param departDateFromFirstStation date of departing from first station of route
     * @return List of ScheduleEntities
     */
    @Override
    public List<ScheduleEntity> findSchedulesForTrain(TrainEntity trainEntity,
                                                      Date departDateFromFirstStation) throws RailroadDaoException {
        return scheduleDao.findSchedulesForTrain(trainEntity,departDateFromFirstStation);
    }

    /**
     * Getting all departure dates from first station of route of train
     * @param trainEntity train
     * @return List of Dates
     */
    @Override
    public List<Date> getDepartDatesForTrain(TrainEntity trainEntity) throws RailroadDaoException {
        return scheduleDao.getDepartDatesForTrain(trainEntity);
    }

    /**
     * Delete schedule for selected train by departure date from first station of route
     * @param trainEntity train
     * @param departDate date of departing from first station of route
     */
    @Override
    public void removeSchedulesByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        scheduleDao.removeScheduleByTrainAndDepartDate(trainEntity, departDate);
    }

    /**
     * Getting schedule for train by station and departure date from first station of route
     * @param trainEntity train
     * @param stationEntity station
     * @param departDate departure date
     * @return ScheduleEntity
     */
    @Override
    public ScheduleEntity getScheduleByTrainAndStationAndDate(TrainEntity trainEntity, StationEntity stationEntity, Date departDate) throws RailroadDaoException {
        return scheduleDao.getScheduleByTrainAndStationAndDate(trainEntity, stationEntity, departDate);
    }

    /**
     * Getting scheduleUpdateDto by train and departure date from first station
     * @param trainEntity train
     * @param departDate departure train
     * @return ScheduleUpdateDto
     */
    @Override
    public ScheduleUpdateDto getScheduleByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
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

    /**
     * Getting actual schedule
     * @param currentDate current date
     * @return List of ScheduleMessageInfoDto for sending message to scoreboard
     */
    @Override
    public List<ScheduleMessageInfoDto> getActualSchedule(Date currentDate) throws RailroadDaoException {
        return scheduleMessageInfoDtoMapper.scheduleEntitieToScheduleMessageInfoDtos(scheduleDao.getActualSchedules(currentDate));
    }

    /**
     * Getting trains numbers which contains in schedule
     * @return List of Integer
     */
    public List<Integer> getTrainsNumberFromSchedule() throws RailroadDaoException {
        return scheduleDao.getTrainsNumberFromSchedule();
    }

    /**
     * Getting new Schedule Entities for train
     * @param scheduleTrainDto scheduleDto for selected train
     * @return List of new Schedule Entities
     */
    private List<ScheduleEntity> getNewSchedules(ScheduleTrainDto scheduleTrainDto) throws RailroadDaoException {
        TrainEntity train = trainDao.findTrainByNumber(scheduleTrainDto.getNumber());
        return createSchedule(train, scheduleTrainDto.getStations(), scheduleTrainDto.getArrivalDates(),
                scheduleTrainDto.getDepartDates(), null);
    }

    /**
     * Mapping scheduleDto to new schedule entity
     * @param train train
     * @param stations stations of route
     * @param arrDate arrival dates
     * @param depDate departure dates
     * @param oldDateFromFirstStation old departure date from first station of train route for updating
     * @return List of new Schedule Entities
     */
    private List<ScheduleEntity> createSchedule(TrainEntity train, List<String> stations, List<String> arrDate,
                                                List<String> depDate,
                                                String oldDateFromFirstStation) throws RailroadDaoException {
        List<ScheduleEntity> schedules = new ArrayList<>();
        Date dateFromFirstStation = getDate(depDate.get(0), "yyyy-MM-dd");
        for(int i = 0; i < stations.size(); i++){
            StationEntity stationEntity = stationDao.findByStationName(stations.get(i));
            ScheduleEntity scheduleEntity;
            if(oldDateFromFirstStation == null){
               scheduleEntity = new ScheduleEntity();
               scheduleEntity.setTrainEntity(train);
               scheduleEntity.setStationEntity(stationEntity);
            }else{
                scheduleEntity = getScheduleByTrainAndStationAndDate(train, stationEntity,
                        getDate(oldDateFromFirstStation, "yyyy-MM-dd"));
            }
            scheduleEntity.setDepartDateFromFirstStation(dateFromFirstStation);
            scheduleEntity.setArrivalDate(getDate(arrDate.get(i), "yyyy-MM-dd HH:mm"));
            scheduleEntity.setDepartDate(getDate(depDate.get(i), "yyyy-MM-dd HH:mm"));
            schedules.add(scheduleEntity);
        }
        return schedules;

    }

    /**
     * Getting updated Schedule Entities for train
     * @param scheduleUpdateDto scheduleDto for selected train
     * @return List of new Schedule Entities
     */
    private List<ScheduleEntity> getChangedSchedules(ScheduleUpdateDto scheduleUpdateDto) throws RailroadDaoException {
        TrainEntity train = trainDao.findTrainByNumber(scheduleUpdateDto.getNumber());
        return createSchedule(train, scheduleUpdateDto.getStations(), scheduleUpdateDto.getArrivalDates(),
                scheduleUpdateDto.getDepartDates(), scheduleUpdateDto.getOldDepartDateFromFirstStation());
    }

}
