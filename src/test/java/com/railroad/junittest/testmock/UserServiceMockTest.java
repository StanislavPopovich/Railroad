package com.railroad.junittest.testmock;

import com.railroad.dao.api.UserGenericDao;
import com.railroad.entity.UserEntity;
import com.railroad.mapper.UserEntityDtoMapper;
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
public class UserServiceMockTest {

    private UserEntity user;

    @Mock
    private UserEntityDtoMapper userDtoMapper;

    @Mock
    private UserGenericDao userDao;

    @InjectMocks
    private UserServiceImpl userService;



    @Before
    public void setup(){
        user = new UserEntity();
        user.setUserName("UserNameMockTest");
        user.setId(100L);
        user.setPassword("password");
    }

    @Test
    public void testGetAll(){
        when(userDao.getAll()).thenReturn(new ArrayList<UserEntity>());
        userService.getAll();
        verify(userDtoMapper).userEntitiesToUserDtos(userDao.getAll());
    }

    @Test
    public void testIsAlreadyExist(){
        when(userDao.getCountUserBuUserName(user.getUserName())).thenReturn(1L);
        userService.isAlreadyExist(user.getUserName());
        verify(userDao).getCountUserBuUserName(user.getUserName());
    }
}
