package com.railroad.service.impl;

import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTrasferTargetDto;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.TicketService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchTrainService extends BaseService{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainEntityDtoMapper trainEntityDtoMapper;

    @Autowired
    private TicketService ticketService;

    @Transactional
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

    @Transactional
    public List<TrainTrasferTargetDto> getTransferTrains(String departStation, String arrivalStation, Date departDate){
        //getting schedules for station on departing date
        List<ScheduleEntity> departStationSchedule = scheduleService.
                getSchedulesByStationNameAndDepartDate(departStation, departDate);

        //creating list for target trains
        List<TrainTrasferTargetDto> trains = new ArrayList<>();

        //date format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for(ScheduleEntity scheduleEntity: departStationSchedule){
            TrainEntity firstTrain = scheduleEntity.getTrainEntity();
            List<StationEntity> firstTrainStations = firstTrain.getStationEntities();
            int indexOfDepartStation = geiIndexOfStation(firstTrainStations, departStation);
            List<ScheduleEntity> trainSchedules = scheduleService.
                    findSchedulesForTrain(firstTrain, scheduleEntity.getDepartDate());
            for(int i = indexOfDepartStation + 1; i < trainSchedules.size(); i++){
                ScheduleEntity arrivalSchedule = trainSchedules.get(i);
                List<ScheduleEntity> scheduleForTransferStation = scheduleService.
                        getSchedulesByStationNameAndDepartDate(arrivalSchedule.getStationEntity().getName(),
                                arrivalSchedule.getArrivalDate());
                for(ScheduleEntity trasferStationSchedule: scheduleForTransferStation){
                    TrainEntity secondTrain = trasferStationSchedule.getTrainEntity();
                    if(secondTrain.getNumber() != firstTrain.getNumber()){
                        List<StationEntity> secondTrainStations = secondTrain.getStationEntities();
                        int indexOfTrasferStation = geiIndexOfStation(secondTrainStations, arrivalSchedule.getStationEntity().getName());
                        int indexOfArrivalStation = geiIndexOfStation(secondTrainStations, arrivalStation);
                        if(checkRouteOfTrain(indexOfTrasferStation, indexOfArrivalStation)){
                            TrainTargetDto firstTrainDto = trainEntityDtoMapper.
                                    trainEntityToTrainSearchDto(firstTrain);

                            List<ScheduleEntity> firstTrainSchedules = getScheduleForTrainInOrder(firstTrain,
                                    scheduleEntity.getDepartDateFromFirstStation());
                            int firstTrainTickets = getCountTickets(firstTrainSchedules, firstTrain, indexOfDepartStation, i);
                            if(firstTrainDto.getSeats() - firstTrainTickets > 0){
                                TrainTargetDto secondTrainDto = trainEntityDtoMapper.
                                        trainEntityToTrainSearchDto(secondTrain);
                                List<ScheduleEntity> secondTrainSchedules = getScheduleForTrainInOrder(secondTrain,
                                        trasferStationSchedule.getDepartDateFromFirstStation());
                                int secondTrainTickets = getCountTickets(secondTrainSchedules, secondTrain,
                                        indexOfTrasferStation, indexOfArrivalStation);
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
                                    TrainTrasferTargetDto train = new TrainTrasferTargetDto();
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
        System.out.println("\n");
        System.out.println("size " + trains.size());
        System.out.println("\n");
        for(TrainTrasferTargetDto train: trains){
            System.out.println(train.getTransferStation());
            System.out.println(train.getSeats());
            System.out.println(train.getTrains().get(0).toString());
            System.out.println(train.getTrains().get(1).toString());
            System.out.println("-------------------------");
        }
        return trains;
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

    private boolean checkRouteOfTrain(int indexOfDepartStation, int indexOfArrivalStation){
        if((indexOfDepartStation != -1 && indexOfArrivalStation != -1) &&
                (indexOfDepartStation < indexOfArrivalStation)){
            return true;
        }
        return false;
    }

    private List<ScheduleEntity> getScheduleForTrainInOrder(TrainEntity trainEntity, Date departDateFromFirstStation){
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

    private boolean checkDepartDate(Date departDate){
        DateTime dateTime = new DateTime(new Date());
        return departDate.getTime() > (dateTime.plusMinutes(10)).toDate().getTime();
    }

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
}
