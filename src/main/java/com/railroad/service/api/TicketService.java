package com.railroad.service.api;

import com.railroad.dto.ticket.TicketDto;
import com.railroad.entity.*;
import com.railroad.exceptions.RailroadDaoException;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

public interface TicketService {

    TicketDto save(TicketEntity ticketEntity) throws RailroadDaoException;

    Long getCountTicketByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart,
                                           ScheduleEntity arrival) throws RailroadDaoException;

    List<TicketDto> getNotActualTickets(UserEntity currentUser) throws RailroadDaoException;

    List<TicketDto> getActualTickets(UserEntity currentUser) throws RailroadDaoException;

    void removeTicketByNumber(Long ticketNumber) throws RailroadDaoException;

    List<TicketDto> getPassengerActualTickets(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException;

    List<TicketDto> getPassengerNotActualTickets(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException;

    List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    List<PassengerEntity> getTrainPassengers(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    boolean isPassengerAlreadyBoughtTicket(PassengerEntity passengerEntity, TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

}
