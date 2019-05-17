package com.railroad.junittest.testmock;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dto.station.StationDto;
import com.railroad.entity.StationEntity;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.StationEntityDtoMapper;
import com.railroad.service.impl.StationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Stanislav Popovich
 */
@RunWith(MockitoJUnitRunner.class)
public class StationServiceMockTest {

    private StationEntity station;


    @Mock
    private StationGenericDao stationGenericDao;

    @Mock
    private StationEntityDtoMapper stationDtoMapper;

    @InjectMocks
    private StationServiceImpl stationService;

    @Before
    public void setup(){
        station = new StationEntity();
        station.setName("StationMockTest");
        station.setId(100L);
    }

    @Test
    public void testGetStationById() throws RailroadDaoException {
        when(stationGenericDao.getById(100L)).thenReturn(station);
        stationService.getStationById(station.getId());
        verify(stationGenericDao).getById(station.getId());

    }

    @Test
    public void testGetStationByName() throws RailroadDaoException {
        when(stationGenericDao.findByStationName("StationMockTest")).thenReturn(station);
        stationService.getStationByName(station.getName());
        verify(stationGenericDao).findByStationName(station.getName());
    }

    @Test
    public void testGetAll() throws RailroadDaoException {
        when(stationGenericDao.getAll()).thenReturn(new ArrayList<StationEntity>());
        stationService.getAll();
        verify(stationGenericDao).getAll();
    }

}
