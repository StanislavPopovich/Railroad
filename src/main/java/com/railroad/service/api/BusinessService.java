package com.railroad.service.api;
import com.railroad.dto.*;
import com.railroad.model.PassengerEntity;

import java.util.Date;
import java.util.List;

public interface BusinessService {


    void saveTicket(TicketDto ticketDto);


    /**
     * The method returns direct trains from starting station to destination station on a specific date
     * @param startStation
     * @param destStation
     * @param date
     * @return list of trainDto
     */
    List<TrainDto> getDirectTrains(String startStation, String destStation, Date date);


    /**
     * The method returns all routes from starting station to destination station
     * @param startStation
     * @param endStation
     * @return list of Strings
     */
    List<String> getAllRoutes(String startStation, String endStation);








}
