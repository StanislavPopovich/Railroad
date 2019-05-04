package com.railroad.dao.api;

import com.railroad.model.*;

import java.util.Date;
import java.util.List;

/**
 * DAO for the {@link TicketEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TicketGenericDao extends GenericDao<TicketEntity, Long> {

    List<TicketEntity> getActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity);

    List<TicketEntity> getNotActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity);

    List<TicketEntity> getActualTickets(UserEntity userEntity);

    List<TicketEntity> getNotActualTickets(UserEntity userEntity);

    Long getCountTicketsByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival);


    void removeTicketByNumber(Long ticketNumber);

    List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);

    List<PassengerEntity> getTrainPassengers(TrainEntity trainEntity, Date departDate);

    Long isPassengerBoughtTicket(PassengerEntity passengerEntity, TrainEntity trainEntity, Date departDate);

    Long getIdByTicket(TicketEntity ticket);
}
