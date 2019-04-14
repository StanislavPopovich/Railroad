package com.railroad.mapper;

import com.railroad.dto.PassengerDto;
import com.railroad.dto.TicketDto;
import com.railroad.dto.TrainTicketDto;
import com.railroad.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketDtoMapper {

    @Mapping(source = "passengerEntity", target = "passengerDto")
    @Mapping(source = "ticketEntity", target = "trainTicketDto")
    TicketDto ticketEntityToTicketDto(TicketEntity ticketEntity);

    List<TicketDto> ticketEntitiesToTicketDtos(List<TicketEntity> ticketEntities);

    PassengerDto passengerEntityToPassengerDto(PassengerEntity passengerEntity);

    default TrainTicketDto trainEntityToTrainTicketDto(TicketEntity ticketEntity){
        if (ticketEntity.getTrainEntity() == null) {
            return null;
        } else {
            TrainTicketDto trainTicketDto = new TrainTicketDto();
            trainTicketDto.setStations(this.stationEntitiesToStationsNames(ticketEntity.getTrainEntity().getStationEntities()));
            trainTicketDto.setNumber(ticketEntity.getTrainEntity().getNumber());
            trainTicketDto.setDepartDate(dateToString(ticketEntity.getDepartDate().getDepartDate()));
            trainTicketDto.setArrivalDate(dateToString(ticketEntity.getArrivalDate().getDepartDate()));
            return trainTicketDto;
        }
    }


    default LinkedList<String> stationEntitiesToStationsNames(List<StationEntity> stationEntities){
        LinkedList<String> stations = new LinkedList<>();
        for(StationEntity stationEntity: stationEntities){
            stations.add(stationEntity.getName());
        }
        return stations;
    }

    default String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dateAndTime = format.format(date);
        return dateAndTime;
    }
}
