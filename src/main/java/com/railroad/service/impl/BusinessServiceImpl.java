package com.railroad.service.impl;
import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.dto.*;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.*;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BusinessServiceImpl implements BusinessService {

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
    private PassengerServiceImpl passengerService;



    @Transactional
    @Override
    public void saveTicket(TicketDto ticketDto) {

        //getting current User
        UserEntity currentUser = userService.getCurrentUser();

        //getting passengerEntity
        PassengerEntity passenger = passengerEntityDtoMapper.passengerDtoToEntity(ticketDto.getPassengerDto());
        if(!passengerService.isAlreadyExist(passenger)){

            //this passenger not exist in db
            passengerService.savePassenger(passenger);
        }

        //getting current passenger from db
        PassengerEntity currentPassenger = passengerService.findPassengerByNameAndBirthDate(passenger);

        //getting passenger of current user
        Set<PassengerEntity> userPassengers = currentUser.getPassengerEntities();

        //adding current passenger to current user
        userPassengers.add(currentPassenger);
        currentUser.setPassengerEntities(userPassengers);

        //getting trainEntity by trainNumber
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(ticketDto.getTrainTicketDto().getNumber());

        //creating new ticketEntity and setting fields
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPassengerEntity(currentPassenger);
        //getting schedule for departing station

        ScheduleEntity departStationSchedule = scheduleService.findScheduleByTrainAndDepartDate(trainEntity,
                getDate(ticketDto.getTrainTicketDto().getDepartDate()));

        //getting schedule for arrival station
        ScheduleEntity arrivalStationSchedule = scheduleService.findScheduleByTrainAndArrivalDate(trainEntity,
                getDate(ticketDto.getTrainTicketDto().getArrivalDate()));
        ticketEntity.setTrainEntity(trainEntity);
        ticketEntity.setDepartDate(departStationSchedule);
        ticketEntity.setArrivalDate(arrivalStationSchedule);
        ticketEntity.setUserEntity(currentUser);

        //saving ticket to db
        ticketService.saveTicket(ticketEntity);

    }

    /**
     * The methods returns Date in format dd-MM-yyyy HH:mm:ss
     * @param date
     * @return date
     */
    private Date getDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date result;
        try {
            result = format.parse(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }




    @Transactional
    @Override
    public List<TrainSearchDto> getDirectTrains(String departStation, String arrivalStation, Date departDate) {

        //getting schedules for station on departing date
        List<ScheduleEntity> departStationSchedule = scheduleService.
                getSchedulesByStationNameAndDepartDate(departStation, departDate);

        //creating list for target trains
        List<TrainSearchDto> trains = new ArrayList<>();

        //date format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for(ScheduleEntity scheduleEntity: departStationSchedule){
            TrainEntity trainEntity = scheduleEntity.getTrainEntity();
            List<StationEntity> trainStation = trainEntity.getStationEntities();

            int indexOfDepartStation = geiIndexOfStation(trainStation, departStation);
            int indexOfArrivalStation = geiIndexOfStation(trainStation, arrivalStation);

            if(checkRouteOfTrain(indexOfDepartStation, indexOfArrivalStation)){
                TrainSearchDto trainSearchDto = trainEntityDtoMapper.
                        trainEntityToTrainSearchDto(scheduleEntity.getTrainEntity());

                List<ScheduleEntity> trainSchedules = scheduleService.
                        findSchedulesForTrain(trainEntity, scheduleEntity.getDepartDateFromFirstStation());

                //getting count tickets from first station of train until destination station
                int tickets = getCountTickets(trainSchedules, trainEntity, indexOfDepartStation, indexOfArrivalStation);

                //setting trainDto fields
                for(ScheduleEntity schedule: trainSchedules){
                    if(schedule.getStationEntity().getName().equals(arrivalStation)){
                        trainSearchDto.setDepartDate(format.format(scheduleEntity.getDepartDate()));
                        trainSearchDto.setArrivalDate(format.format(schedule.getArrivalDate()));
                        trainSearchDto.setSeats(trainSearchDto.getSeats() - tickets);
                    }
                }
                trains.add(trainSearchDto);
            }
        }
        return trains;
    }

    private boolean checkRouteOfTrain(int indexOfDepartStation, int indexOfArrivalStation){
        if((indexOfDepartStation != -1 && indexOfArrivalStation != -1) &&
                (indexOfDepartStation < indexOfArrivalStation)){
            return true;
        }
        return false;
    }

    private int geiIndexOfStation(List<StationEntity> trainStation, String station){
        int index = -1;
        for(int i = 0; i < trainStation.size(); i++){
            if(trainStation.get(i).getName().equals(station)){
                index = i;
            }
        }
        return index;
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

    /**
     * Returns count of tickets in the interesting part of route
     * @param trainSchedules - schedule of current train
     * @param trainEntity - current train
     * @param departStationIndex - index of departing station
     * @param arrivalStationIndex - index of arrival station
     * @return count of tickets of current train
     */
    private int getCountTickets(List<ScheduleEntity> trainSchedules, TrainEntity trainEntity,
                                int departStationIndex, int arrivalStationIndex){
        int countTickets = 0;

        //getting matrix of ticket for all train route
        int[][] tickets = getTicketMatrix(trainSchedules,trainEntity);

        //getting the number of tickets in the interesting part of route
        for(int i = trainSchedules.size() - 1; i > departStationIndex; i--){
            for(int j = 0; j < arrivalStationIndex; j++){
                countTickets += tickets[i][j];
            }
        }
        return countTickets;
    }



    private int[][] getTicketMatrix(List<ScheduleEntity> trainSchedule, TrainEntity trainEntity){
        int[][] ticketMatrix = getEmptyMatrix(trainSchedule.size());
        for(int i = 0; i < trainSchedule.size() - 1; i++){
            for(int j = trainSchedule.size() - 1; j > i; j--){
                ticketMatrix[j][i] = ticketService.getCountTicketByTrainAndSchedules(trainEntity,
                        trainSchedule.get(i), trainSchedule.get(j)).intValue();
            }
        }
        return ticketMatrix;
    }


    @Transactional
    @Override
    public List<String> getAllRoutes(String departStation, String arrivalStation) {
        List<String> targetRoutes = new ArrayList<>();
        List<String> routes = getRoutes(departStation, arrivalStation);
        for(String str: routes){
            StringBuilder route = new StringBuilder();
            String[] stationsId = str.split(",");
            for(int i = 0; i < stationsId.length; i++){
                if(i == stationsId.length - 1){
                    route.append(stationService.getStationById(new Long(stationsId[i])).getName());
                }else{
                    route.append(stationService.getStationById(new Long(stationsId[i])).getName() + "=>");
                }
            }
            targetRoutes.add(route.toString());
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
    public List<List<TicketDto>> getAllTickets() {
        //getting current User
        UserEntity currentUser = userService.getCurrentUser();
        List<List<TicketDto>> tickets = new ArrayList<>();
        tickets.add(ticketService.getActualTickets(currentUser));
        tickets.add(ticketService.getAllTickets(currentUser));
        return tickets;
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

    private int[][] getEmptyMatrix(int size){
        int[][] matrix = new int[size][size];
        for(int row = 0; row< matrix.length; row++){
            for(int column = 0; column < matrix[row].length; column++){
                matrix[row][column] = 0;
            }
        }
        return matrix;
    }


}
