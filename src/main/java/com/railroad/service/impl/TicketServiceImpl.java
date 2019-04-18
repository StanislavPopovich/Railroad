package com.railroad.service.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.dto.TicketDto;
import com.railroad.mapper.TicketDtoMapper;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;
import com.railroad.model.UserEntity;
import com.railroad.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketGenericDao ticketDao;

    @Autowired
    private TicketDtoMapper ticketDtoMapper;


    @Transactional
    @Override
    //+
    public void saveTicket(TicketEntity ticketEntity) {
        ticketDao.save(ticketEntity);
    }

    public Long getCountTicketByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival){
        return ticketDao.getCountTicketsByTrainAndSchedules(trainEntity, depart, arrival);
    }

    @Override
    public List<TicketDto> getAllTickets(UserEntity currentUser) {
        List<TicketDto> tickets = ticketDtoMapper.ticketEntitiesToTicketDtos(ticketDao.getAllTickets(currentUser));
        return tickets;
    }

    @Override
    public List<TicketDto> getActualTickets(UserEntity currentUser) {
        return ticketDtoMapper.ticketEntitiesToTicketDtos(ticketDao.getActualTickets(currentUser));
    }

    @Transactional
    @Override
    public void removeTicketByNumber(Long ticketNumber) {
        ticketDao.removeTicketByNumber(ticketNumber);
    }
}
