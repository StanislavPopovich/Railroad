package com.railroad.service.api;
import com.railroad.dto.*;
import com.railroad.model.PassengerEntity;

import java.util.Date;
import java.util.List;

public interface BusinessService {


    void saveTicket(TicketDto ticketDto);


    /**
     * The method returns direct trains from starting station to destination station on a specific date
     * @param departStation
     * @param arrivalStation
     * @param departDate
     * @return list of trainDto
     */
    List<TrainSearchDto> getDirectTrains(String departStation, String arrivalStation, Date departDate);


    /**
     * Returns all routes from departing station to arrival station
     * @param departStation departing station of route
     * @param arrivalStation arrival station of route
     * @return list of strings which is routes from departing station to arrival station
     */
    List<String> getAllRoutes(String departStation, String arrivalStation);

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






}
