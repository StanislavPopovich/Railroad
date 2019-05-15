package com.railroad.service.impl;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.dto.ticket.TrainTicketDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.entity.*;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author Stanislav Popovich
 */

@Service
public class BuyTicketServiceImpl extends BaseService implements BuyTicketService {

    private static final Logger logger = Logger.getLogger(BuyTicketServiceImpl.class);

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
    private ScheduleService scheduleService;

    @Autowired
    private EmailService emailService;

    /**
     * Saving ticket in db
     * @param globalTrainsTicketDto tickets
     * @param passengerDto passenger
     */
    @Transactional
    @Override
    public void saveTicket(GlobalTrainsTicketDto globalTrainsTicketDto, PassengerDto passengerDto) throws RailroadDaoException {
        List<TrainTicketDto> trains = new ArrayList<>();
        trains.add(globalTrainsTicketDto.getToTrain().getFirstTrain());
        if(globalTrainsTicketDto.getToTrain().getSecondTrain() != null){
            trains.add(globalTrainsTicketDto.getToTrain().getSecondTrain());
        }
        if(globalTrainsTicketDto.getReturnTrain() != null){
            if(globalTrainsTicketDto.getReturnTrain().getFirstTrain() != null){
                trains.add(globalTrainsTicketDto.getReturnTrain().getFirstTrain());
            }
            if(globalTrainsTicketDto.getReturnTrain().getSecondTrain() != null){
                trains.add(globalTrainsTicketDto.getReturnTrain().getSecondTrain());
            }
        }

        List<TicketDto> tickets = new ArrayList<>();

        //getting current passenger from db
        PassengerEntity currentPassenger = getCurrentPassengerFromDB(passengerEntityDtoMapper.
                passengerDtoToEntity(passengerDto));

        //getting current User
        UserEntity currentUser = getCurrentUser(currentPassenger);

        for(TrainTicketDto train: trains){
            TrainEntity currentTrain = getCurrentTrain(train);
            TicketEntity ticket = getTicketByTicketDto(train, currentUser, currentPassenger, currentTrain);
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

            tickets.add(ticketDto);
        }

        emailService.sendMail(tickets, "new");
    }

    /**
     * Saving passenger in db
     * @param passenger passenger
     */
    private void savePassengerIfNotExistInDB(PassengerEntity passenger) throws RailroadDaoException {
        if(!passengerService.isAlreadyExist(passenger)){
            //this passenger is not exist in db
            passengerService.save(passenger);
        }
    }

    /**
     * Getting Passenger from db
     * @param passenger passenger
     * @return PassengerEntity
     */
    private PassengerEntity getCurrentPassengerFromDB(PassengerEntity passenger) throws RailroadDaoException {
        savePassengerIfNotExistInDB(passenger);
        return passengerService.findPassengerByNameAndBirthDate(passenger);
    }

    /**
     * Getting current user with all passengers
     * @param passenger passenger
     * @return UserEntity
     */
    private UserEntity getCurrentUser(PassengerEntity passenger) throws RailroadDaoException {
        //getting current User
        UserEntity currentUser = userService.getCurrentUser();

        //getting passenger of current user
        Set<PassengerEntity> userPassengers = currentUser.getPassengerEntities();

        //adding current passenger to current user
        userPassengers.add(passenger);
        currentUser.setPassengerEntities(userPassengers);
        return currentUser;
    }

    /**
     * Getting current TrainEntity
     * @param trainTicketDto train
     * @return TrainEntity
     */
    private TrainEntity getCurrentTrain(TrainTicketDto trainTicketDto) throws RailroadDaoException {
        //getting trainEntity by trainNumber
        TrainEntity trainEntity = trainService.findTrainByNumber(trainTicketDto.getNumber());
        return trainEntity;
    }

    /**
     * Mapping ticketDto ti TicketEntity
     * @param trainTicketDto ticketDto
     * @param currentUser current user
     * @param currentPassenger current passenger
     * @param currentTrain current train
     * @return TicketEntity
     */
    private TicketEntity getTicketByTicketDto(TrainTicketDto trainTicketDto, UserEntity currentUser,
                                              PassengerEntity currentPassenger, TrainEntity currentTrain) throws RailroadDaoException {

        //creating new ticketEntity and setting fields
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPassengerEntity(currentPassenger);
        ticketEntity.setTrainEntity(currentTrain);
        ticketEntity.setDepartDate(getDepartStationSchedule(currentTrain, trainTicketDto));
        ticketEntity.setArrivalDate(getArrivalStationSchedule(currentTrain, trainTicketDto));
        ticketEntity.setUserEntity(currentUser);
        return ticketEntity;
    }

    /**
     * Getting schedule for departure station
     * @param trainEntity train
     * @param trainTicketDto ticketDto
     * @return ScheduleEntity
     */
    private ScheduleEntity getDepartStationSchedule(TrainEntity trainEntity, TrainTicketDto trainTicketDto) throws RailroadDaoException {
        ScheduleEntity departStationSchedule = scheduleService.findScheduleByTrainAndDepartDate(trainEntity,
                getDate(trainTicketDto.getDepartDate(),"dd-MM-yyyy HH:mm"));
        return departStationSchedule;
    }

    /**
     * Getting schedule for arrival station
     * @param trainEntity train
     * @param trainTicketDto ticketDto
     * @return ScheduleEntity
     */
    private ScheduleEntity getArrivalStationSchedule(TrainEntity trainEntity, TrainTicketDto trainTicketDto) throws RailroadDaoException {
        ScheduleEntity arrivalStationSchedule = scheduleService.findScheduleByTrainAndArrivalDate(trainEntity,
                getDate(trainTicketDto.getArrivalDate(),"dd-MM-yyyy HH:mm"));
        return arrivalStationSchedule;
    }

}
