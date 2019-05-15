package com.railroad.dao.api;

import com.railroad.entity.*;
import com.railroad.exceptions.RailroadDaoException;

import java.util.Date;
import java.util.List;

/**
 * DAO for the {@link TicketEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TicketGenericDao extends GenericDao<TicketEntity, Long> {

    List<TicketEntity> getActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException;

    List<TicketEntity> getNotActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException;

    List<TicketEntity> getActualTickets(UserEntity userEntity) throws RailroadDaoException;

    List<TicketEntity> getNotActualTickets(UserEntity userEntity) throws RailroadDaoException;

    Long getCountTicketsByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival) throws RailroadDaoException;


    void removeTicketByNumber(Long ticketNumber) throws RailroadDaoException;

    List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    List<PassengerEntity> getTrainPassengers(TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    Long isPassengerBoughtTicket(PassengerEntity passengerEntity, TrainEntity trainEntity, Date departDate) throws RailroadDaoException;

    Long getIdByTicket(TicketEntity ticket) throws RailroadDaoException;
}
