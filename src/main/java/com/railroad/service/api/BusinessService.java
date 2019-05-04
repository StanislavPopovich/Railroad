package com.railroad.service.api;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.route.RouteDto;
import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainScheduleDto;
import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.dto.way.WayDto;

import java.util.Date;
import java.util.List;

public interface BusinessService {



    /**
     * The method returns direct trains from starting station to destination station on a specific date
     * @param departStation
     * @param arrivalStation
     * @param departDate
     * @return list of trainDto
     */
    List<TrainTargetDto> getDirectTrains(String departStation, String arrivalStation, Date departDate);


    /**
     * Returns all routes from departing station to arrival station
     * @param departStation departing station of route
     * @param arrivalStation arrival station of route
     * @return list of strings which is routes from departing station to arrival station
     */
    List<RouteDto> getAllRoutes(String departStation, String arrivalStation);

    /**
     *
     * @param wayDto
     */
    void saveStationAndWay(WayDto wayDto);

    List<TrainScheduleDto> getTrainsFromSchedule(String station, Date date);

    List<TicketDto> getNotActualTickets();

    List<TicketDto> getActualTickets();

    List<PassengerDto> getPassengersOfCurrentUser();

    List<TicketDto> getPassengerActualTickets(PassengerDto passengerDto);

    List<TicketDto> getPassengerNotActualTickets(PassengerDto passengerDto);

    List<String> getDepartDatesForTrain(Integer trainNumber);

    void removeTrainFromSchedule(Integer trainNumber, String departingDate);

    void updateSchedule(ScheduleUpdateDto scheduleUpdateDto);

    List<ScheduleUpdateDto> getScheduleUpdateDtosByTrainAdnDate(Integer trainNumber, Date departDate);

    List<PassengerDto> getTrainPassengers(Integer trainNumber, Date departDate);

    List<ScheduleMessageInfoDto> getActualSchedule();

    public boolean isPassengerAlreadyBoughtTicket(TrainTicketDto trainTicketDto, PassengerDto passengerDto);






}
