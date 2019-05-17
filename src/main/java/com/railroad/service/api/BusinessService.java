package com.railroad.service.api;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.dto.schedule.ScheduleTrainDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.*;
import com.railroad.dto.way.WayDto;
import com.railroad.exceptions.RailroadDaoException;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

public interface BusinessService {


    void saveStationAndWay(WayDto wayDto) throws RailroadDaoException;

    List<TrainScheduleDto> getTrainsFromSchedule(String station, Date date) throws RailroadDaoException;

    List<TicketDto> getNotActualTickets() throws RailroadDaoException;

    List<TicketDto> getActualTickets() throws RailroadDaoException;

    List<PassengerDto> getPassengersOfCurrentUser() throws RailroadDaoException;

    List<TicketDto> getPassengerActualTickets(PassengerDto passengerDto) throws RailroadDaoException;

    List<TicketDto> getPassengerNotActualTickets(PassengerDto passengerDto) throws RailroadDaoException;

    List<String> getDepartDatesForTrain(Integer trainNumber) throws RailroadDaoException;

    void removeTrainFromSchedule(Integer trainNumber, String departingDate) throws RailroadDaoException;


    ScheduleUpdateDto getScheduleUpdateDtosByTrainAdnDate(Integer trainNumber, Date departDate) throws RailroadDaoException;

    List<PassengerDto> getTrainPassengers(Integer trainNumber, Date departDate) throws RailroadDaoException;

    List<ScheduleMessageInfoDto> getActualSchedule() throws RailroadDaoException;

    int isPassengerAlreadyBoughtTicket(GlobalTrainsTicketDto globalTrainsTicketDto, PassengerDto passengerDto) throws RailroadDaoException;

    boolean[][] saveTrain(TrainDto trainDto) throws RailroadDaoException;

    boolean isTrainAlreadyExistsInSchedule(ScheduleTrainDto scheduleTrainDto) throws RailroadDaoException;

    boolean isTrainAlreadyExistsInSchedule(ScheduleUpdateDto scheduleUpdateDto) throws RailroadDaoException;






}
