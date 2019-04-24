package com.railroad.mapper;

import com.railroad.dto.PassengerDto;
import com.railroad.dto.TicketDto;
import com.railroad.dto.TrainTicketDto;
import com.railroad.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketDtoMapper {

    @Mapping(source = "passengerEntity", target = "passengerDto")
    @Mapping(source = "ticketEntity", target = "trainTicketDto")
    @Mapping(source = "id", target = "number")
    TicketDto ticketEntityToTicketDto(TicketEntity ticketEntity);

    TicketEntity ticketDtoToTicketEntity(TicketDto ticketDto);

    List<TicketDto> ticketEntitiesToTicketDtos(List<TicketEntity> ticketEntities);

    @Mapping(source ="birthDate", dateFormat = "dd-MM-yyyy",target = "birthDate")
    PassengerDto passengerEntityToPassengerDto(PassengerEntity passengerEntity);

    @Mapping(source ="ticketEntity.departDate.departDate", dateFormat = "dd-MM-yyyy HH:mm",target = "departDate")
    @Mapping(source ="ticketEntity.arrivalDate.arrivalDate", dateFormat = "dd-MM-yyyy HH:mm",target = "arrivalDate")
    @Mapping(source ="ticketEntity.departDate.stationEntity.name", target = "departStation")
    @Mapping(source ="ticketEntity.arrivalDate.stationEntity.name", target = "arrivalStation")
    @Mapping(source ="ticketEntity.trainEntity.number", target = "number")
    TrainTicketDto trainEntityToTrainTicketDto(TicketEntity ticketEntity);



}
