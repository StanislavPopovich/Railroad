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

    List<TicketEntity> getAllTickets(UserEntity userEntity);

    List<TicketEntity> getActualTickets(UserEntity userEntity);

    Long getCountTicketsByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival);

    List<PassengerEntity> getAllUserPassengers(UserEntity userEntity);
}
