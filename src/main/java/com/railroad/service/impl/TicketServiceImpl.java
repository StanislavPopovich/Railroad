package com.railroad.service.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.TicketDtoMapper;
import com.railroad.entity.*;
import com.railroad.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketGenericDao ticketDao;

    @Autowired
    private TicketDtoMapper ticketDtoMapper;


    /**
     * Saving ticket to db
     * @param ticketEntity ticket
     * @return TicketDto
     */
    @Override
    public TicketDto save(TicketEntity ticketEntity) throws RailroadDaoException {
        ticketDao.save(ticketEntity);
        ticketEntity.setId(ticketDao.getIdByTicket(ticketEntity));
        TicketDto ticketDto = ticketDtoMapper.ticketEntityToTicketDto(ticketEntity);
        return ticketDto;
    }

    /**
     * Getting count of tickets for train
     * @param trainEntity train
     * @param depart schedule of departure station
     * @param arrival schedule of arrival station
     * @return Long
     */
    @Override
    public Long getCountTicketByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival) throws RailroadDaoException {
        return ticketDao.getCountTicketsByTrainAndSchedules(trainEntity, depart, arrival);
    }

    /**
     * Getting not actual tickets for current user account
     * @param currentUser user
     * @return List of TicketDto
     */
    @Override
    public List<TicketDto> getNotActualTickets(UserEntity currentUser) throws RailroadDaoException {
        List<TicketDto> tickets = ticketDtoMapper.ticketEntitiesToTicketDtos(ticketDao.getNotActualTickets(currentUser));
        return tickets;
    }

    /**
     * Getting actual tickets for current user account
     * @param currentUser user
     * @return List of TicketDto
     */
    @Override
    public List<TicketDto> getActualTickets(UserEntity currentUser) throws RailroadDaoException {
        return ticketDtoMapper.ticketEntitiesToTicketDtos(ticketDao.getActualTickets(currentUser));
    }

    /**
     * Removing ticket from db
     * @param ticketNumber ticket number
     */
    @Transactional
    @Override
    public void removeTicketByNumber(Long ticketNumber) throws RailroadDaoException {
        ticketDao.removeTicketByNumber(ticketNumber);
    }

    /**
     * Getting actual tickets for passenger
     * @param userEntity user
     * @param passengerEntity passenger
     * @return List TicketDto
     */
    @Override
    public List<TicketDto> getPassengerActualTickets(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException {
        List<TicketEntity> tickets = ticketDao.getActualTicketsForPassenger(userEntity, passengerEntity);
        return ticketDtoMapper.ticketEntitiesToTicketDtos(tickets);
    }

    /**
     * Getting not actual tickets for passenger
     * @param userEntity user
     * @param passengerEntity passenger
     * @return List TicketDto
     */
    @Override
    public List<TicketDto> getPassengerNotActualTickets(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException {
        List<TicketEntity> tickets = ticketDao.getNotActualTicketsForPassenger(userEntity, passengerEntity);
        return ticketDtoMapper.ticketEntitiesToTicketDtos(tickets);
    }

    /**
     * Getting tickets train on specific schedule
     * @param trainEntity train
     * @param departDate departure date
     * @return List of TicketEntity
     */
    @Override
    public List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        return ticketDao.getTicketsByTrainAndDepartDate(trainEntity, departDate);
    }

    /**
     * Getting passengers which have bought tickets on train
     * @param trainEntity train
     * @param departDate departure date
     * @return List of PassengerEntity
     */
    @Override
    public List<PassengerEntity> getTrainPassengers(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        return ticketDao.getTrainPassengers(trainEntity, departDate);
    }

    @Override
    public boolean isPassengerAlreadyBoughtTicket(PassengerEntity passengerEntity, TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        Long countTickets = ticketDao.isPassengerBoughtTicket(passengerEntity, trainEntity, departDate);
        if(countTickets == 0){
            return false;
        }
        return true;
    }
}
