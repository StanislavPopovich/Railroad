package com.railroad.junittest.testmock;

import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.train.TrainDto;
import com.railroad.entity.TrainEntity;
import com.railroad.entity.UserEntity;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.service.impl.TrainServiceImpl;
import com.railroad.service.impl.UserServiceImpl;
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
public class TrainServiceMockTest {

    private TrainEntity trainEntity;

    private TrainDto trainDto;

    @Mock
    private TrainGenericDao trainDao;

    @Mock
    private TrainEntityDtoMapper mapper;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Before
    public void setup(){
        trainEntity = new TrainEntity();
        trainEntity.setNumber(100);
        trainDto = new TrainDto();
        trainDto.setNumber(100);
    }


    @Test
    public void testIsAlreadyExist(){
        when(trainDao.getCountTrains(trainEntity.getNumber())).thenReturn(1L);
        trainService.isAlreadyExist(trainEntity.getNumber());
        verify(trainDao).getCountTrains(trainEntity.getNumber());
    }

    @Test
    public void testGetAll(){
        when(trainDao.getAll()).thenReturn(new ArrayList<TrainEntity>());
        trainService.getAll();
        verify(mapper).trainEntitiesToTrainDto(trainDao.getAll());
    }

    @Test
    public void testFindTrainByNumber(){
        when(trainDao.findTrainByNumber(trainEntity.getNumber())).thenReturn(trainEntity);
        trainService.findTrainByNumber(trainEntity.getNumber());
        verify(trainDao).findTrainByNumber(trainEntity.getNumber());
    }

    @Test
    public void testFindTrainDtoByNumber(){
        when(trainDao.findTrainByNumber(trainEntity.getNumber())).thenReturn(trainEntity);
        trainService.getTrainDtoByNumber(trainEntity.getNumber());
        verify(mapper).trainEntityToTrainDto(trainEntity);
    }

    @Test
    public void testGetAllTrainsNumbers(){
        when(trainDao.getAllTrainsNumbers()).thenReturn(new ArrayList<Integer>());
        trainService.getAllTrainsNumbers();
        verify(trainDao).getAllTrainsNumbers();
    }

}
