package com.railroad.service.impl;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.route.RouteDto;
import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.station.StationDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainDto;
import com.railroad.dto.train.TrainScheduleDto;
import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.dto.way.WayDto;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.RouteDtoMapper;
import com.railroad.mapper.TicketDtoMapper;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.*;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BusinessServiceImpl extends BaseService implements BusinessService {

    private static final Logger logger = Logger.getLogger(BusinessServiceImpl.class);

    private List<Integer> list;
    private boolean[] visited;
    private int size;
    private int[][] graph;

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
    private RouteDtoMapper routeDtoMapper;



    @Transactional
    @Override
    public List<TrainTargetDto> getDirectTrains(String departStation, String arrivalStation, Date departDate) {
        //getting schedules for station on departing date
        List<ScheduleEntity> departStationSchedule = scheduleService.
                getSchedulesByStationNameAndDepartDate(departStation, departDate);

        //creating list for target trains
        List<TrainTargetDto> trains = new ArrayList<>();

        //date format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for(ScheduleEntity scheduleEntity: departStationSchedule){
                TrainEntity trainEntity = scheduleEntity.getTrainEntity();
                List<StationEntity> trainStation = trainEntity.getStationEntities();

               /* int indexOfDepartStation = geiIndexOfStation(trainStation, departStation);
                int indexOfArrivalStation = geiIndexOfStation(trainStation, arrivalStation);

                if(checkRouteOfTrain(indexOfDepartStation, indexOfArrivalStation)){
                    TrainTargetDto trainTargetDto = trainEntityDtoMapper.
                            trainEntityToTrainSearchDto(scheduleEntity.getTrainEntity());

                    List<ScheduleEntity> trainSchedules = getScheduleForTrainInOrder(trainEntity,
                            scheduleEntity.getDepartDateFromFirstStation());

                        //getting count tickets from first station of train until destination station
                        int tickets = getCountTickets(trainSchedules, trainEntity, indexOfDepartStation, indexOfArrivalStation);

                        //setting trainDto fields
                        for(ScheduleEntity schedule: trainSchedules){
                            if(checkDepartDate(schedule.getDepartDate())){
                                if(schedule.getStationEntity().getName().equals(arrivalStation)){
                                    trainTargetDto.setDepartDate(format.format(scheduleEntity.getDepartDate()));
                                    trainTargetDto.setArrivalDate(format.format(schedule.getArrivalDate()));
                                    trainTargetDto.setSeats(trainTargetDto.getSeats() - tickets);
                                    trains.add(trainTargetDto);
                                }
                            }
                        }

                }*/

        }
        return trains;
    }









    public List<TrainDto> getTrainsWithIneTransfer(String departStation, String arrivalStation, Date departDate){
        //getting schedules for station on departing date
        List<ScheduleEntity> departStationSchedule = scheduleService.
                getSchedulesByStationNameAndDepartDate(departStation, departDate);
        List<List<TrainDto>> trainsWithTransfer = new ArrayList<>();

        //date format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for(ScheduleEntity scheduleEntity: departStationSchedule){
            TrainEntity trainEntity = scheduleEntity.getTrainEntity();
            System.out.println();
            System.out.println(scheduleEntity.getStationEntity().getName());
            System.out.println(trainEntity.getNumber());

            List<ScheduleEntity> trainSchedules = scheduleService.
                    findSchedulesForTrain(trainEntity, scheduleEntity.getDepartDate());
            int index = 0;
            for(int i = 0; i < trainSchedules.size(); i++){
                if(trainSchedules.get(i).getStationEntity().getName().equals(departStation))
                    index = i;
            }
            for(int i = index; i < trainSchedules.size(); i++){
                System.out.println();
                System.out.println(trainSchedules.size());
                System.out.println(trainSchedules.get(i).getStationEntity().getName());
                System.out.println("--------------");
                ScheduleEntity trainSchedule = trainSchedules.get(i);
                StationEntity station = trainSchedule.getStationEntity();
                List<ScheduleEntity> secondSchedules = scheduleService.
                        getSchedulesByStationNameAndDepartDate(station.getName(), trainSchedule.getDepartDate());

                System.out.println();
                for(ScheduleEntity ss: secondSchedules){
                    System.out.println(ss.getStationEntity().getName());
                    System.out.println(ss.getTrainEntity().getNumber());
                    System.out.println(ss.getDepartDate().toString());
                }

            }
        }



        return null;
    }








    @Transactional
    @Override
    public List<RouteDto> getAllRoutes(String departStation, String arrivalStation) {
        List<RouteDto> targetRoutes = new ArrayList<>();
        List<String> routes = getRoutes(departStation, arrivalStation);
        for(String route: routes){
            String[] stationsArr = route.split(",");
            List<String> stations = new ArrayList<>();
            for(int i = 0; i < stationsArr.length; i++){
                stations.add(stationService.getStationById(new Long(stationsArr[i])).getName());
            }
            targetRoutes.add(routeDtoMapper.stringToRoute(stations));
        }
        return targetRoutes;
    }

    @Override
    public void saveStationAndWay(WayDto wayDto) {
        StationDto stationDto = new StationDto();
        stationDto.setName(wayDto.getFirstStation());
        stationService.save(stationDto);
        wayService.save(wayDto);

    }

    @Override
    public List<TrainScheduleDto> getTrainsFromSchedule(String station, Date date) {
        //getting schedules for station on departing date
        List<ScheduleEntity> stationSchedule = scheduleService.
                getSchedulesByStationNameAndDepartDate(station, date);
        List<TrainScheduleDto> trainScheduleDtos = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: stationSchedule){
            trainScheduleDtos.add(trainEntityDtoMapper.trainEntityToTrainScheduleDto(scheduleEntity.getTrainEntity()));
        }
        return trainScheduleDtos;
    }

    @Override
    @Transactional
    public List<TicketDto> getNotActualTickets() {
        //getting current User
        UserEntity currentUser = userService.getCurrentUser();
        return ticketService.getNotActualTickets(currentUser);
    }

    @Override
    @Transactional
    public List<TicketDto> getActualTickets() {
        UserEntity currentUser = userService.getCurrentUser();
        return ticketService.getActualTickets(currentUser);
    }

    @Transactional
    @Override
    public List<PassengerDto> getPassengersOfCurrentUser() {
        UserEntity currentUser = userService.getCurrentUser();
        List<PassengerDto> passengers = passengerEntityDtoMapper.passengerEntitiesToPassengerDtos(currentUser.getPassengerEntities());
        return passengers;
    }

    @Transactional
    @Override
    public List<TicketDto> getPassengerNotActualTickets(PassengerDto passengerDto) {
        UserEntity currentUser = userService.getCurrentUser();
        PassengerEntity passengerEntity = passengerService.
                findPassengerByNameAndBirthDate(passengerEntityDtoMapper.passengerDtoToEntity(passengerDto));
        return ticketService.getPassengerNotActualTickets(currentUser, passengerEntity);
    }

    @Override
    public List<String> getDepartDatesForTrain(Integer trainNumber) {
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainNumber);
        List<Date> dates = scheduleService.getDepartDatesForTrain(trainEntity);
        List<String> strDates = new ArrayList<>();
        for(Date date: dates){
            strDates.add(date.toString());
        }
        return strDates;
    }

    @Transactional
    @Override
    public void removeTrainFromSchedule(Integer trainNumber, String departingDate) {
        Date departDate = getDate(departingDate, "yyyy-MM-dd");
        TrainEntity train = trainService.findTrainEntityByNumber(trainNumber);
        List<TicketEntity> tickets = ticketService.getTicketsByTrainAndDepartDate(train, departDate);
        for(TicketEntity ticket: tickets){
            // send emails
            ticketService.removeTicketByNumber(ticket.getId());
        }
        scheduleService.removeSchedulesByTrainAndDepartDate(train, departDate);


    }

    @Transactional
    @Override
    public void updateSchedule(ScheduleUpdateDto scheduleUpdateDto) {
        TrainEntity train = trainService.findTrainEntityByNumber(scheduleUpdateDto.getTrainNumber());
        StationEntity station = stationService.getStationEntityByStationName(scheduleUpdateDto.getStationName());
        Date departDate = getDate(scheduleUpdateDto.getOldDepartDateFromFirstStation(), "yyyy-MM-dd");
        ScheduleEntity scheduleEntity = scheduleService.getScheduleByTrainAndStationAndDate(train, station, departDate);
        scheduleEntity.setDepartDate(getDate(scheduleUpdateDto.getDepartDate(), "yyyy-MM-dd HH:mm"));
        scheduleEntity.setArrivalDate(getDate(scheduleUpdateDto.getArrivalDate(), "yyyy-MM-dd HH:mm"));
        scheduleEntity.setDepartDateFromFirstStation(getDate(scheduleUpdateDto.getDepartDateFromFirstStation(), "yyyy-MM-dd"));
        scheduleService.updateSchedule(scheduleEntity);

    }
    @Transactional
    @Override
    public List<ScheduleUpdateDto> getScheduleUpdateDtosByTrainAdnDate(Integer trainNumber, Date departDate) {
        TrainEntity train = trainService.findTrainEntityByNumber(trainNumber);
        return scheduleService.getScheduleByTrainAndDepartDate(train, departDate);
    }

    @Transactional
    @Override
    public List<PassengerDto> getTrainPassengers(Integer trainNumber, Date departDate) {
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainNumber);
        List<PassengerEntity> passengers = ticketService.getTrainPassengers(trainEntity, departDate);
        return passengerEntityDtoMapper.passengerEntitiesToPassengerDtos(passengers);
    }

    @Override
    @Transactional
    public List<ScheduleMessageInfoDto> getActualSchedule() {
        return scheduleService.getActualSchedule(new Date());
    }

    @Transactional
    @Override
    public boolean isPassengerAlreadyBoughtTicket(TrainTicketDto trainTicketDto, PassengerDto passengerDto) {
        PassengerEntity passengerEntity = passengerEntityDtoMapper.passengerDtoToEntity(passengerDto);
        if(!passengerService.isAlreadyExist(passengerEntity)){
            return false;
        }
        passengerEntity = passengerService.findPassengerByNameAndBirthDate(passengerEntity);
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainTicketDto.getNumber());
        ScheduleEntity scheduleEntity = scheduleService.
                findScheduleByTrainAndDepartDate(trainEntity, getDate(trainTicketDto.getDepartDate(), "dd-MM-yyyy HH:mm"));

        return ticketService.isPassengerAlreadyBoughtTicket(passengerEntity, trainEntity, scheduleEntity.getDepartDateFromFirstStation());
    }

    @Transactional
    @Override
    public List<TicketDto> getPassengerActualTickets(PassengerDto passengerDto) {
        UserEntity currentUser = userService.getCurrentUser();
        PassengerEntity passengerEntity = passengerService.
                findPassengerByNameAndBirthDate(passengerEntityDtoMapper.passengerDtoToEntity(passengerDto));
        return ticketService.getPassengerActualTickets(currentUser, passengerEntity);
    }


    private List<String> getRoutes(String startStation, String endStation) {
        list = new ArrayList<>();
        int countStations = stationService.getIdOfLastStation();
        visited = new boolean[countStations + 1];
        graph = getSmegMatrix(wayService.getAll(), countStations);
        size = 0;
        List<String> allRoutes = dfs(stationService.getStationEntityByStationName(startStation).getId().intValue(),
                stationService.getStationEntityByStationName(endStation).getId().intValue(), new ArrayList<>());
        return allRoutes;
    }

    private List<String> dfs(int startStationId, int endStationId, List<String> allRoutes){
        //station added to path
        list.add(startStationId);
        size++;

        //station marked as visited
        visited[startStationId] = true;

        //when the destination station is found
        if(startStationId == endStationId){
            StringBuffer sb = new StringBuffer();
            for(Integer station: list){
                sb.append(station + ",");
            }
            allRoutes.add(sb.toString());
        }

        for(int i = 1; i<= visited.length - 1; i++){
            // if there's an edge between  startStationId and i station
            if(graph[startStationId][i] == 1){
                //and that station is not visited yet
                if(visited[i] == false){
                    //start dfs from that station
                    allRoutes = dfs(i, endStationId, allRoutes);
                    visited[i] = false;
                    size--;
                    list.remove(size);
                }
            }
        }
        return allRoutes;
    }

    private int[][] getSmegMatrix(List<WayEntity> wayEntities, int countStation){
        int[][] matrix = getEmptyMatrix(countStation + 1);
        for(WayEntity wayEntity : wayEntities){
            matrix[wayEntity.getFirstStationEntity().getId().intValue()][wayEntity.getSecondStationEntity().getId().intValue()] = 1;
            matrix[wayEntity.getSecondStationEntity().getId().intValue()][wayEntity.getFirstStationEntity().getId().intValue()] = 1;
        }
        return matrix;
    }




}
