package com.railroad.service.api;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.route.RouteDto;
import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainDto;
import com.railroad.dto.train.TrainScheduleDto;
import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.dto.way.WayDto;

import java.util.Date;
import java.util.List;

public interface BusinessService {


    void saveStationAndWay(WayDto wayDto);

    List<TrainScheduleDto> getTrainsFromSchedule(String station, Date date);

    List<TicketDto> getNotActualTickets();

    List<TicketDto> getActualTickets();

    List<PassengerDto> getPassengersOfCurrentUser();

    List<TicketDto> getPassengerActualTickets(PassengerDto passengerDto);

    List<TicketDto> getPassengerNotActualTickets(PassengerDto passengerDto);

    List<String> getDepartDatesForTrain(Integer trainNumber);

    void removeTrainFromSchedule(Integer trainNumber, String departingDate);


    ScheduleUpdateDto getScheduleUpdateDtosByTrainAdnDate(Integer trainNumber, Date departDate);

    List<PassengerDto> getTrainPassengers(Integer trainNumber, Date departDate);

    List<ScheduleMessageInfoDto> getActualSchedule();

    boolean isPassengerAlreadyBoughtTicket(TrainTicketDto trainTicketDto, PassengerDto passengerDto);

    boolean[][] saveTrain(TrainDto trainDto);






}
