package com.railroad.service.impl;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.TicketDtoMapper;
import com.railroad.model.*;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.TicketService;
import com.railroad.service.api.TrainService;
import com.railroad.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class BuyTicketService extends BaseService {

    private static final Logger logger = Logger.getLogger(BuyTicketService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketDtoMapper ticketDtoMapper;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public TicketDto saveTicket(TrainTicketDto trainTicketDto, PassengerDto passengerDto) {

        //getting current passenger from db
        PassengerEntity currentPassenger = getCurrentPassengerFromDB(passengerEntityDtoMapper.
                passengerDtoToEntity(passengerDto));

        //getting current User
        UserEntity currentUser = getCurrentUser(currentPassenger);

        TrainEntity currentTrain = getCurrentTrain(trainTicketDto);

        TicketEntity ticket = getTicketByTicketDto(trainTicketDto, currentUser, currentPassenger, currentTrain);
        ticket.setEmail(passengerDto.getEmail());

        //saving ticket to db
        TicketDto ticketDto = ticketService.save(ticket);

        ticketDto.getPassengerDto().setEmail(passengerDto.getEmail());

        logger.info("User " + currentUser.getUserName() + " bought ticket for " +
                currentPassenger.getLastName() + " " + currentPassenger.getName() + "\n" + "Train number: "
                + currentTrain.getNumber() + "\n" + "From " + ticket.getDepartDate().getStationEntity().getName() +
                " to " + ticket.getArrivalDate().getStationEntity().getName() + "\n" +
                "DepartDate: " + ticket.getDepartDate().getDepartDate() +  "\n" +
                "ArrivalDate: " + ticket.getArrivalDate().getArrivalDate());

        emailService.sendMail(ticketDto, "new");
        return ticketDto;
    }

    private void savePassengerIfNotExistInDB(PassengerEntity passenger){
        if(!passengerService.isAlreadyExist(passenger)){
            //this passenger is not exist in db
            passengerService.save(passenger);
        }
    }

    private PassengerEntity getCurrentPassengerFromDB(PassengerEntity passenger){
        savePassengerIfNotExistInDB(passenger);
        return passengerService.findPassengerByNameAndBirthDate(passenger);
    }

    private UserEntity getCurrentUser(PassengerEntity passenger){
        //getting current User
        UserEntity currentUser = userService.getCurrentUser();

        //getting passenger of current user
        Set<PassengerEntity> userPassengers = currentUser.getPassengerEntities();

        //adding current passenger to current user
        userPassengers.add(passenger);
        currentUser.setPassengerEntities(userPassengers);
        return currentUser;
    }

    private TrainEntity getCurrentTrain(TrainTicketDto trainTicketDto){
        //getting trainEntity by trainNumber
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainTicketDto.getNumber());
        return trainEntity;
    }

    private TicketEntity getTicketByTicketDto(TrainTicketDto trainTicketDto, UserEntity currentUser,
                                              PassengerEntity currentPassenger, TrainEntity currentTrain){

        //creating new ticketEntity and setting fields
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPassengerEntity(currentPassenger);
        ticketEntity.setTrainEntity(currentTrain);
        ticketEntity.setDepartDate(getDepartStationSchedule(currentTrain, trainTicketDto));
        ticketEntity.setArrivalDate(getArrivalStationSchedule(currentTrain, trainTicketDto));
        ticketEntity.setUserEntity(currentUser);
        return ticketEntity;
    }

    private ScheduleEntity getDepartStationSchedule(TrainEntity trainEntity, TrainTicketDto trainTicketDto){
        ScheduleEntity departStationSchedule = scheduleService.findScheduleByTrainAndDepartDate(trainEntity,
                getDate(trainTicketDto.getDepartDate(),"dd-MM-yyyy HH:mm"));
        return departStationSchedule;
    }

    private ScheduleEntity getArrivalStationSchedule(TrainEntity trainEntity, TrainTicketDto trainTicketDto){
        ScheduleEntity arrivalStationSchedule = scheduleService.findScheduleByTrainAndArrivalDate(trainEntity,
                getDate(trainTicketDto.getArrivalDate(),"dd-MM-yyyy HH:mm"));
        return arrivalStationSchedule;
    }

}
