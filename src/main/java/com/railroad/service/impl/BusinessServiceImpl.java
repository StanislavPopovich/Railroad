package com.railroad.service.impl;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.route.RouteDto;
import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.dto.schedule.ScheduleTrainDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.station.StationDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.ticket.TrainTicketDto;
import com.railroad.dto.ticket.TrainTransferTicketDto;
import com.railroad.dto.train.*;
import com.railroad.dto.way.WayDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.entity.*;
import com.railroad.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @author Stanislav Popovich
 */

@Service
public class BusinessServiceImpl extends BaseService implements BusinessService {


    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private WayService wayService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainEntityDtoMapper trainEntityDtoMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private RouteService routeService;

    /**
     * Save new station and new way that merged this station with other station
     * @param wayDto wayDto
     */
    @Override
    public void saveStationAndWay(WayDto wayDto) throws RailroadDaoException {
        StationDto stationDto = new StationDto();
        stationDto.setName(wayDto.getFirstStation());
        stationService.save(stationDto);
        wayService.save(wayDto);

    }

    /**
     * Getting schedule for selected station and departure date
     * @param station station
     * @param date departure date
     * @return List of TrainScheduleDto
     */
    @Override
    public List<TrainScheduleDto> getTrainsFromSchedule(String station, Date date) throws RailroadDaoException {
        //getting schedules for station on departing date
        List<ScheduleEntity> stationSchedule = scheduleService.
                getScheduleByStationAndDepartDate(station, date);
        List<TrainScheduleDto> trainScheduleDtos = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: stationSchedule){
            trainScheduleDtos.add(trainEntityDtoMapper.trainEntityToTrainScheduleDto(scheduleEntity.getTrainEntity()));
        }
        return trainScheduleDtos;
    }


    /**
     * Getting not actual tickets for current user
     * @return List of TicketDtos
     */
    @Override
    @Transactional
    public List<TicketDto> getNotActualTickets() throws RailroadDaoException {
        //getting current User
        UserEntity currentUser = userService.getCurrentUser();
        return ticketService.getNotActualTickets(currentUser);
    }

    /**
     * Getting actual tickets for current user account
     * @return List of TicketDtos
     */
    @Override
    @Transactional
    public List<TicketDto> getActualTickets() throws RailroadDaoException {
        UserEntity currentUser = userService.getCurrentUser();
        return ticketService.getActualTickets(currentUser);
    }

    /**
     * Getting passengers of current user account
     * @return List of PassengerDtos
     */
    @Transactional
    @Override
    public List<PassengerDto> getPassengersOfCurrentUser() throws RailroadDaoException {
        UserEntity currentUser = userService.getCurrentUser();
        List<PassengerDto> passengers = passengerEntityDtoMapper.passengerEntitiesToPassengerDtos(currentUser.getPassengerEntities());
        return passengers;
    }

    /**
     * Getting not actual tickets of selected passenger
     * @param passengerDto passengerDto
     * @return List of TicketDtos
     */
    @Transactional
    @Override
    public List<TicketDto> getPassengerNotActualTickets(PassengerDto passengerDto) throws RailroadDaoException {
        UserEntity currentUser = userService.getCurrentUser();
        PassengerEntity passengerEntity = passengerService.
                findPassengerByNameAndBirthDate(passengerEntityDtoMapper.passengerDtoToEntity(passengerDto));
        return ticketService.getPassengerNotActualTickets(currentUser, passengerEntity);
    }

    /**
     * Getting departure dates of selected train
     * @param trainNumber train number
     * @return  List of Strings
     */
    @Override
    public List<String> getDepartDatesForTrain(Integer trainNumber) throws RailroadDaoException {
        TrainEntity trainEntity = trainService.findTrainByNumber(trainNumber);
        List<Date> dates = scheduleService.getDepartDatesForTrain(trainEntity);
        List<String> strDates = new ArrayList<>();
        for(Date date: dates){
            strDates.add(date.toString());
        }
        return strDates;
    }

    /**
     * Delete train from schedule
     * @param trainNumber train number
     * @param departingDate departure date
     */
    @Transactional
    @Override
    public void removeTrainFromSchedule(Integer trainNumber, String departingDate) throws RailroadDaoException {
        Date departDate = getDate(departingDate, "yyyy-MM-dd");
        TrainEntity train = trainService.findTrainByNumber(trainNumber);
        List<TicketEntity> tickets = ticketService.getTicketsByTrainAndDepartDate(train, departDate);
        for(TicketEntity ticket: tickets){
            // send emails
            ticketService.removeTicketByNumber(ticket.getId());
        }
        scheduleService.removeSchedulesByTrainAndDepartDate(train, departDate);
    }

    /**
     * Getting schedule for train on date
     * @param trainNumber train number
     * @param departDate departure date
     * @return
     */
    @Transactional
    @Override
    public ScheduleUpdateDto getScheduleUpdateDtosByTrainAdnDate(Integer trainNumber, Date departDate) throws RailroadDaoException {
        TrainEntity train = trainService.findTrainByNumber(trainNumber);
        return scheduleService.getScheduleByTrainAndDepartDate(train, departDate);
    }

    /**
     * Getting all passenger that bought tickets on train
     * @param trainNumber train number
     * @param departDate departure date
     * @return List of PassengerDto
     */
    @Transactional
    @Override
    public List<PassengerDto> getTrainPassengers(Integer trainNumber, Date departDate) throws RailroadDaoException {
        TrainEntity trainEntity = trainService.findTrainByNumber(trainNumber);
        List<PassengerEntity> passengers = ticketService.getTrainPassengers(trainEntity, departDate);
        return passengerEntityDtoMapper.passengerEntitiesToPassengerDtos(passengers);
    }

    /**
     * Getting actual schedule for sending message to scoreboard
     * @return List of ScheduleMessageInfoDto
     */
    @Override
    @Transactional
    public List<ScheduleMessageInfoDto> getActualSchedule() throws RailroadDaoException {
        return scheduleService.getActualSchedule(new Date());
    }

    /**
     * Checking that passenger have already bought ticket
     * @param globalTrainsTicketDto tickets
     * @param passengerDto passenger
     * @return number of ticket
     */
    @Transactional
    @Override
    public int isPassengerAlreadyBoughtTicket(GlobalTrainsTicketDto globalTrainsTicketDto, PassengerDto passengerDto) throws RailroadDaoException {
        List<TrainTicketDto> tickets = getTrainTickets(globalTrainsTicketDto);

        PassengerEntity passengerEntity = passengerEntityDtoMapper.passengerDtoToEntity(passengerDto);
        if(!passengerService.isAlreadyExist(passengerEntity)){
           return 0;
        }
        passengerEntity = passengerService.findPassengerByNameAndBirthDate(passengerEntity);
        for(int i = 0; i < tickets.size(); i++){
            TrainEntity trainEntity = trainService.findTrainByNumber(tickets.get(i).getNumber());
            ScheduleEntity scheduleEntity = scheduleService.
                    findScheduleByTrainAndDepartDate(trainEntity,
                            getDate(tickets.get(i).getDepartDate(), "dd-MM-yyyy HH:mm"));
            if(ticketService.isPassengerAlreadyBoughtTicket(passengerEntity,
                    trainEntity, scheduleEntity.getDepartDateFromFirstStation())){
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Parsing globalTrainsTicketDto
     * @param globalTrainsTicketDto tickets
     * @return List of TrainTicketDto
     */
    private List<TrainTicketDto> getTrainTickets(GlobalTrainsTicketDto globalTrainsTicketDto){
        List<TrainTicketDto> tickets = new ArrayList<>();
        TrainTransferTicketDto firstTransfer = globalTrainsTicketDto.getToTrain();
        tickets.add(firstTransfer.getFirstTrain());
        if(firstTransfer.getSecondTrain() != null){
            tickets.add(firstTransfer.getSecondTrain());
        }
        if(globalTrainsTicketDto.getReturnTrain() != null){
            tickets.add(globalTrainsTicketDto.getReturnTrain().getFirstTrain());
            if(globalTrainsTicketDto.getReturnTrain().getSecondTrain() != null){
                tickets.add(globalTrainsTicketDto.getReturnTrain().getSecondTrain());
            }
        }
        return tickets;
    }

    /**
     * Saving train to db
     * @param trainDto train
     * @return error array
     */
    @Transactional
    @Override
    public boolean[][] saveTrain(TrainDto trainDto) throws RailroadDaoException {
        boolean [][] error = new boolean[2][2];
        error[0][0] = checkLength(trainDto.getNumber());
        error[0][1] = checkLength(trainDto.getSeats());
        if(!error[0][0] || !error[0][1]){
            return error;
        }
        error[1][0] = trainAlreadyExist(trainDto.getNumber());
        error[1][1] = checkRouteExist(trainDto.getStations());
        if(!error[1][0] || !error[1][1]){
            return error;
        }
        trainService.save(trainDto);
        return error;
    }

    @Transactional
    @Override
    public boolean isTrainAlreadyExistsInSchedule(ScheduleTrainDto scheduleTrainDto) throws RailroadDaoException {
        TrainEntity train = trainService.findTrainByNumber(scheduleTrainDto.getNumber());
        List<Date> departDates = new ArrayList<>();
        for(String depart: scheduleTrainDto.getDepartDates()){
            departDates.add(getDate(depart.split(" ")[0], "yyyy-MM-dd"));
        }
        for(Date date: departDates){
            if(scheduleService.getScheduleByTrainAndDepartDay(train, date) > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTrainAlreadyExistsInSchedule(ScheduleUpdateDto scheduleUpdateDto) throws RailroadDaoException {
        ScheduleTrainDto scheduleTrainDto = new ScheduleTrainDto();
        scheduleTrainDto.setNumber(scheduleUpdateDto.getNumber());
        scheduleTrainDto.setDepartDates(scheduleUpdateDto.getDepartDates());
        if(scheduleUpdateDto.getOldDepartDateFromFirstStation().split(" ")[0].
                equals(scheduleUpdateDto.getDepartDates().get(0).split(" ")[0])){
            return false;
        }else {
            return isTrainAlreadyExistsInSchedule(scheduleTrainDto);
        }
    }

    /**
     * Getting passenger actual tickets
     * @param passengerDto passenger
     * @return List of TicketDtos
     */
    @Transactional
    @Override
    public List<TicketDto> getPassengerActualTickets(PassengerDto passengerDto) throws RailroadDaoException {
        UserEntity currentUser = userService.getCurrentUser();
        PassengerEntity passengerEntity = passengerService.
                findPassengerByNameAndBirthDate(passengerEntityDtoMapper.passengerDtoToEntity(passengerDto));
        return ticketService.getPassengerActualTickets(currentUser, passengerEntity);
    }

    /**
     * Checking length of train number or counts of seats
     * @param trainDtoFields train number or train seats
     * @return boolean
     */
    private boolean checkLength(Integer trainDtoFields){
        if(trainDtoFields.toString().length() > 4){
            return false;
        }
        return true;
    }

    /**
     * Checking that train already exist in db
     * @param trainNumber train number
     * @return boolean
     */
    private boolean trainAlreadyExist(Integer trainNumber) throws RailroadDaoException {
        if(trainService.isAlreadyExist(trainNumber)){
            return false;
        }
        return true;
    }

    /**
     * Checking that route exist
     * @param stations stations
     * @return boolean
     */
    private boolean checkRouteExist(List<String> stations) throws RailroadDaoException {
        List<RouteDto> routes = routeService.getAllRoutes(stations.get(0), stations.get(stations.size() - 1));
        for(RouteDto routeDto: routes){
            int count = 0;
            List<String> routeStations = routeDto.getStations();
            for(int i = 0; i < stations.size(); i++){
                for(int j = 0; j < routeStations.size(); j++){
                    if(stations.get(i).equals(routeStations.get(j))){
                        count++;
                    }
                }
            }
            if(count == stations.size()){
                return true;
            }
        }
        return false;
    }
}
