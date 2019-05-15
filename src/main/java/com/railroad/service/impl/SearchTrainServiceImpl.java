package com.railroad.service.impl;

import com.railroad.dao.api.ScheduleGenericDao;
import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.TrainGenericDao;
import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTransferTargetDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.entity.ScheduleEntity;
import com.railroad.entity.StationEntity;
import com.railroad.entity.TrainEntity;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.SearchTrainService;
import com.railroad.service.api.TicketService;
import com.railroad.service.api.TrainService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class SearchTrainServiceImpl extends BaseService implements SearchTrainService {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainEntityDtoMapper trainEntityDtoMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private ScheduleGenericDao scheduleGenericDao;

    @Transactional
    public List<TrainTransferTargetDto> getAlternativeTrasfer(String departStation, String arrivalStation, Date departDate){
        /*StationEntity depart = stationGenericDao.findByStationName(departStation);
        StationEntity arrival = stationGenericDao.findByStationName(arrivalStation);
        List<TrainEntity> firstTrains = trainGenericDao.getAllByDepartStation(depart);
        List<TrainTransferTargetDto> targetTrains = new ArrayList<>();
        for(TrainEntity firstTrain: firstTrains){
            ScheduleEntity firstTrainStationSchedule = scheduleService.findScheduleByTrainAndDepartDate(firstTrain, departDate);
            if(firstTrainStationSchedule != null){
                List<StationEntity> stations = firstTrain.getStationEntities();

                int indexOfDepartStation = geiIndexOfStation(stations, departStation);

                List<ScheduleEntity> firstTrainSchedule = scheduleService.
                        findSchedulesForTrain(firstTrain, firstTrainStationSchedule.getDepartDateFromFirstStation());

                for(int i = indexOfDepartStation + 1; i < stations.size(); i++){

                    int firstTrainTickets = getCountTickets(firstTrainSchedule, firstTrain, indexOfDepartStation, i);

                    if(firstTrainTickets > 0){

                        StationEntity transferStation = stationGenericDao.findByStationName(stations.get(i).getName());

                        List<Long> directTrainsId = trainGenericDao.getTrainsIdByDepartAndArrivalStations(transferStation, arrival);
                        for(Long id: directTrainsId){
                            TrainEntity transferTrain = trainGenericDao.getById(id);
                            ScheduleEntity transferTrainStationSchedule = scheduleService.
                                    findScheduleByTrainAndDepartDate(transferTrain, departDate);


                            *//*if(scheduleGenericDao.isExistTrainInScheduleByDate(transferTrain, scheduleEntity.getArrivalDate()) > 0){


                                TrainTargetDto firstTrainDto = new TrainTargetDto();

                            }*//*

                        }
                    }


                }
            }

        }

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");*/
        /*for(TrainEntity trainEntity:trains1){
            System.out.println(trainEntity.getNumber());
        }*/
        return null;
    }

    /**
     * Getting direct trains from departure station to arrival station
     * @param departStation departure station
     * @param arrivalStation arrival station
     * @param departDate departure date
     * @return List of TrainTargetDto
     */
    @Override
    @Transactional
    public List<TrainTargetDto> getDirectTrains(String departStation, String arrivalStation, Date departDate) throws RailroadDaoException {
        //getting schedules for station on departing date
        List<ScheduleEntity> departStationSchedule = scheduleService.
                getScheduleByStationAndDepartDate(departStation, departDate);

        //creating list for target trains
        List<TrainTargetDto> trains = new ArrayList<>();

        //date format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for(ScheduleEntity scheduleEntity: departStationSchedule){
            TrainEntity trainEntity = scheduleEntity.getTrainEntity();
            List<StationEntity> trainStation = trainEntity.getStationEntities();

            int indexOfDepartStation = geiIndexOfStation(trainStation, departStation);
            int indexOfArrivalStation = geiIndexOfStation(trainStation, arrivalStation);

            if(checkRouteOfTrain(indexOfDepartStation, indexOfArrivalStation)){
                TrainTargetDto trainTargetDto = trainEntityDtoMapper.
                        trainEntityToTrainSearchDto(scheduleEntity.getTrainEntity());

                List<ScheduleEntity> trainSchedules = getScheduleForTrainInOrder(trainEntity,
                        scheduleEntity.getDepartDateFromFirstStation());

                //getting count tickets from first station of train until destination station
                int tickets = getCountTickets(trainSchedules, trainEntity, indexOfDepartStation, indexOfArrivalStation);
                if(trainTargetDto.getSeats() - tickets > 0){
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
                }


            }

        }
        return trains;
    }


    /**
     * Getting trains with one transfer rom departure station to arrival station
     * @param departStation departure station
     * @param arrivalStation arrival station
     * @param departDate departure date
     * @return List of TrainTransferTargetDto
     */
    @Override
    @Transactional
    public List<TrainTransferTargetDto> getTransferTrains(String departStation, String arrivalStation, Date departDate) throws RailroadDaoException {
        //getting schedules for station on departing date
        List<ScheduleEntity> departStationSchedule = scheduleService.
                getScheduleByStationAndDepartDate(departStation, departDate);

        //creating list for target trains
        List<TrainTransferTargetDto> trains = new ArrayList<>();

        //date format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
      /*  logger.info("depart Station " + departStation);*/
        for(ScheduleEntity scheduleEntity: departStationSchedule){
            TrainEntity firstTrain = scheduleEntity.getTrainEntity();

           /* logger.info("train number: " + firstTrain.getNumber() + " route: " + firstTrain.getStationEntities().get(0) +
                    "-" + firstTrain.getStationEntities().get(firstTrain.getStationEntities().size() - 1));*/

            List<StationEntity> firstTrainStations = firstTrain.getStationEntities();

           /* logger.info("stations:");
            for(StationEntity st: firstTrainStations){
                logger.info(st.getName());
            }*/

            int indexOfDepartStation = geiIndexOfStation(firstTrainStations, departStation);

           /* logger.info("index station: " + indexOfDepartStation);*/

            List<ScheduleEntity> trainSchedules = scheduleService.
                    findSchedulesForTrain(firstTrain, scheduleEntity.getDepartDate());
           /* logger.info("train schedule: ");*/

            for(int i = indexOfDepartStation + 1; i < trainSchedules.size(); i++){

                ScheduleEntity arrivalSchedule = trainSchedules.get(i);

                List<ScheduleEntity> scheduleForTransferStation = scheduleService.
                        getScheduleByStationAndDepartDate(arrivalSchedule.getStationEntity().getName(),
                                arrivalSchedule.getArrivalDate());

                for(ScheduleEntity transferStationSchedule: scheduleForTransferStation){
                    TrainEntity secondTrain = transferStationSchedule.getTrainEntity();
                    if(secondTrain.getNumber() != firstTrain.getNumber()){
                        List<StationEntity> secondTrainStations = secondTrain.getStationEntities();
                        int indexOfTransferStation = geiIndexOfStation(secondTrainStations, arrivalSchedule.getStationEntity().getName());
                        int indexOfArrivalStation = geiIndexOfStation(secondTrainStations, arrivalStation);
                        if(checkRouteOfTrain(indexOfTransferStation, indexOfArrivalStation)){
                            TrainTargetDto firstTrainDto = trainEntityDtoMapper.
                                    trainEntityToTrainSearchDto(firstTrain);

                            List<ScheduleEntity> firstTrainSchedules = getScheduleForTrainInOrder(firstTrain,
                                    scheduleEntity.getDepartDateFromFirstStation());
                            int firstTrainTickets = getCountTickets(firstTrainSchedules, firstTrain, indexOfDepartStation, i);
                            if(firstTrainDto.getSeats() - firstTrainTickets > 0){
                                TrainTargetDto secondTrainDto = trainEntityDtoMapper.
                                        trainEntityToTrainSearchDto(secondTrain);
                                List<ScheduleEntity> secondTrainSchedules = getScheduleForTrainInOrder(secondTrain,
                                        transferStationSchedule.getDepartDateFromFirstStation());
                                int secondTrainTickets = getCountTickets(secondTrainSchedules, secondTrain,
                                        indexOfTransferStation, indexOfArrivalStation);
                                if(secondTrainDto.getSeats() - secondTrainTickets > 0){
                                    for(ScheduleEntity firstTrainSchedule: firstTrainSchedules){
                                        if(checkDepartDate(firstTrainSchedule.getDepartDate())){
                                            if(firstTrainSchedule.getStationEntity().getName().
                                                    equals(trainSchedules.get(i).getStationEntity().getName())){
                                                firstTrainDto.setDepartDate(format.format(scheduleEntity.getDepartDate()));
                                                firstTrainDto.setArrivalDate(format.format(trainSchedules.get(i).getArrivalDate()));
                                                firstTrainDto.setSeats(firstTrainDto.getSeats() - firstTrainTickets);

                                            }
                                        }
                                    }
                                    for(ScheduleEntity secondTrainSchedule: secondTrainSchedules){
                                        if(secondTrainSchedule.getStationEntity().getName().
                                                equals(trainSchedules.get(i).getStationEntity().getName())){
                                            secondTrainDto.setDepartDate(format.format(secondTrainSchedule.getDepartDate()));
                                        }
                                        if(secondTrainSchedule.getStationEntity().getName().equals(arrivalStation)){

                                            secondTrainDto.setArrivalDate(format.format(secondTrainSchedule.getArrivalDate()));
                                            secondTrainDto.setSeats(secondTrainDto.getSeats() - secondTrainTickets);
                                        }
                                    }
                                    TrainTransferTargetDto train = new TrainTransferTargetDto();
                                    List<TrainTargetDto> twoTrains = new ArrayList<>();
                                    twoTrains.add(firstTrainDto);
                                    twoTrains.add(secondTrainDto);
                                    train.setTrains(twoTrains);
                                    train.setTransferStation(trainSchedules.get(i).getStationEntity().getName());
                                    if(firstTrainDto.getSeats() < secondTrainDto.getSeats()){
                                        train.setSeats(firstTrainDto.getSeats());
                                    }else{
                                        train.setSeats(secondTrainDto.getSeats());
                                    }
                                    trains.add(train);
                                }
                            }
                        }
                    }

                }
            }
        }
        return trains;
    }

    /**
     * Getting index of station in route
     * @param trainStations route of train
     * @param station station
     * @return index
     */
    private int geiIndexOfStation(List<StationEntity> trainStations, String station){
        int index = -1;
        for(int i = 0; i < trainStations.size(); i++){
            if(trainStations.get(i).getName().equals(station)){
                index = i;
            }
        }
        return index;
    }

    /**
     * Checking that departure station is before arrival station
     * @param indexOfDepartStation index of departure station
     * @param indexOfArrivalStation index of arrival station
     * @return boolean
     */
    private boolean checkRouteOfTrain(int indexOfDepartStation, int indexOfArrivalStation){
        if((indexOfDepartStation != -1 && indexOfArrivalStation != -1) &&
                (indexOfDepartStation < indexOfArrivalStation)){
            return true;
        }
        return false;
    }

    /**
     * Sorting schedules
     * @param trainEntity train
     * @param departDateFromFirstStation departure date from first staion of route
     * @return List of ScheduleEntity
     */
    private List<ScheduleEntity> getScheduleForTrainInOrder(TrainEntity trainEntity, Date departDateFromFirstStation) throws RailroadDaoException {
        List<ScheduleEntity> result = new ArrayList<>();
        List<ScheduleEntity> trainSchedules = scheduleService.
                findSchedulesForTrain(trainEntity, departDateFromFirstStation);
        for(StationEntity station: trainEntity.getStationEntities()){
            for(ScheduleEntity schedule: trainSchedules){
                if(station.getName().equals(schedule.getStationEntity().getName())){
                    result.add(schedule);
                }
            }
        }
        return result;
    }

    /**
     * Checking that current date more thatndeparture date
     * @param departDate departure date
     * @return boolean
     */
    private boolean checkDepartDate(Date departDate){
        DateTime dateTime = new DateTime(new Date());
        return departDate.getTime() > (dateTime.plusMinutes(10)).toDate().getTime();
    }

    /**
     * Getting count of vacant tickets on train from departure station to arrival station
     * @param trainSchedules schedule of train
     * @param trainEntity train
     * @param departStationIndex index of departure station
     * @param arrivalStationIndex index of arrival station
     * @return  int
     */
    private int getCountTickets(List<ScheduleEntity> trainSchedules, TrainEntity trainEntity,
                                int departStationIndex, int arrivalStationIndex) throws RailroadDaoException {
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

    /**
     * Getting ticket matrix
     * @param trainSchedule train schedule
     * @param trainEntity train
     * @return array
     */
    private int[][] getTicketMatrix(List<ScheduleEntity> trainSchedule, TrainEntity trainEntity) throws RailroadDaoException {
        int[][] ticketMatrix = getEmptyMatrix(trainSchedule.size());
        for(int i = 0; i < trainSchedule.size() - 1; i++){
            for(int j = trainSchedule.size() - 1; j > i; j--){
                ticketMatrix[j][i] = ticketService.getCountTicketByTrainAndSchedules(trainEntity,
                        trainSchedule.get(i), trainSchedule.get(j)).intValue();
            }
        }
        return ticketMatrix;
    }
}
