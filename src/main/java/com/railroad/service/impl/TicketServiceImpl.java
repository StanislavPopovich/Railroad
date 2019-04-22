package com.railroad.service.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.dto.TicketDto;
import com.railroad.mapper.TicketDtoMapper;
import com.railroad.model.*;
import com.railroad.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public List<TicketDto> getNotActualTickets(UserEntity currentUser) {
        List<TicketDto> tickets = ticketDtoMapper.ticketEntitiesToTicketDtos(ticketDao.getNotActualTickets(currentUser));
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

    @Override
    public List<TicketDto> getPassengerActualTickets(UserEntity userEntity, PassengerEntity passengerEntity) {
        List<TicketEntity> tickets = ticketDao.getActualTicketsForPassenger(userEntity, passengerEntity);
        return ticketDtoMapper.ticketEntitiesToTicketDtos(tickets);
    }

    @Override
    public List<TicketDto> getPassengerNotActualTickets(UserEntity userEntity, PassengerEntity passengerEntity) {
        List<TicketEntity> tickets = ticketDao.getNotActualTicketsForPassenger(userEntity, passengerEntity);
        return ticketDtoMapper.ticketEntitiesToTicketDtos(tickets);
    }

    @Override
    public List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        return ticketDao.getTicketsByTrainAndDepartDate(trainEntity, departDate);
    }
}
