package com.railroad.junittest.testmock;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.mapper.ScheduleEntityDtoMapper;
import com.railroad.service.impl.ScheduleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Stanislav Popovich
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceMockTest {

    private ScheduleEntity schedule;

    @Mock
    private ScheduleEntityDtoMapper scheduleMapper;

    @Mock
    private ScheduleGenericDao scheduleDao;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Before
    public void setup(){
        schedule = new ScheduleEntity();
        TrainEntity train = new TrainEntity();
        train.setNumber(1);
        train.setId(1L);
        train.setSeats(100);
        Date departureDate = new Date(622584000000L);
        Date arrivalDate = new Date(622674000000L);
        schedule.setDepartDateFromFirstStation(departureDate);
        schedule.setDepartDate(departureDate);
        schedule.setArrivalDate(arrivalDate);
        schedule.setTrainEntity(train);

    }

    @Test
    public void testGetAll(){
        when(scheduleDao.getAll()).thenReturn(new ArrayList<ScheduleEntity>());
        scheduleService.getAll();
        verify(scheduleMapper).scheduleEntitiesToScheduleDtos(scheduleDao.getAll());
    }

    @Test
    public void testFindScheduleByTrainAndDepartDate(){
        when(scheduleDao.findScheduleByTrainAndDepartDate(schedule.getTrainEntity(), schedule.getDepartDate())).
                thenReturn(schedule);
        scheduleService.findScheduleByTrainAndDepartDate(schedule.getTrainEntity(), schedule.getDepartDate());
        verify(scheduleDao).findScheduleByTrainAndDepartDate(schedule.getTrainEntity(), schedule.getDepartDate());
    }

    @Test
    public void testFindScheduleByTrainAndArrivalDate(){
        when(scheduleDao.findScheduleByTrainAndArrivalDate(schedule.getTrainEntity(), schedule.getArrivalDate())).
                thenReturn(schedule);
        scheduleService.findScheduleByTrainAndArrivalDate(schedule.getTrainEntity(), schedule.getArrivalDate());
        verify(scheduleDao).findScheduleByTrainAndArrivalDate(schedule.getTrainEntity(), schedule.getArrivalDate());
    }

    @Test
    public void testFindSchedulesForTrain(){
        when(scheduleDao.findSchedulesForTrain(schedule.getTrainEntity(), schedule.getDepartDateFromFirstStation())).
                thenReturn(new ArrayList<ScheduleEntity>());
        scheduleService.findSchedulesForTrain(schedule.getTrainEntity(), schedule.getDepartDateFromFirstStation());
        verify(scheduleDao).findSchedulesForTrain(schedule.getTrainEntity(), schedule.getDepartDateFromFirstStation());
    }

    @Test
    public void testGetDepartDatesForTrain(){
        when(scheduleDao.getDepartDatesForTrain(schedule.getTrainEntity())).
                thenReturn(new ArrayList<Date>());
        scheduleService.getDepartDatesForTrain(schedule.getTrainEntity());
        verify(scheduleDao).getDepartDatesForTrain(schedule.getTrainEntity());
    }

    @Test
    public void testGetScheduleByTrainAndStationAndDate(){
        when(scheduleDao.getScheduleByTrainAndStationAndDate(schedule.getTrainEntity(), schedule.getStationEntity(),
                schedule.getDepartDate())).
                thenReturn(schedule);
        scheduleService.getScheduleByTrainAndStationAndDate(schedule.getTrainEntity(), schedule.getStationEntity(),
                schedule.getDepartDate());
        verify(scheduleDao).getScheduleByTrainAndStationAndDate(schedule.getTrainEntity(), schedule.getStationEntity(),
                schedule.getDepartDate());
    }

    @Test
    public void testGetTrainsNumberFromSchedule(){
        when(scheduleDao.getTrainsNumberFromSchedule()).
                thenReturn(new ArrayList<Integer>());
        scheduleService.getTrainsNumberFromSchedule();
        verify(scheduleDao).getTrainsNumberFromSchedule();
    }



}
