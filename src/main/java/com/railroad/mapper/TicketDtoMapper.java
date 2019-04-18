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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketDtoMapper {

    @Mapping(source = "passengerEntity", target = "passengerDto")
    @Mapping(source = "ticketEntity", target = "trainTicketDto")
    @Mapping(source = "id", target = "number")
    TicketDto ticketEntityToTicketDto(TicketEntity ticketEntity);

    List<TicketDto> ticketEntitiesToTicketDtos(List<TicketEntity> ticketEntities);

    @Mapping(source ="birthDate", dateFormat = "dd-MM-yyyy",target = "birthDate")
    PassengerDto passengerEntityToPassengerDto(PassengerEntity passengerEntity);

    default TrainTicketDto trainEntityToTrainTicketDto(TicketEntity ticketEntity){
        if (ticketEntity.getTrainEntity() == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String departStation = ticketEntity.getDepartDate().getStationEntity().getName();
            String arrivalStation = ticketEntity.getArrivalDate().getStationEntity().getName();
            TrainTicketDto trainTicketDto = new TrainTicketDto();
            List<String> stations = new ArrayList<>();
            stations.add(departStation);
            stations.add(arrivalStation);
            trainTicketDto.setStations(stations);
            trainTicketDto.setNumber(ticketEntity.getTrainEntity().getNumber());
            trainTicketDto.setDepartDate(format.format(ticketEntity.getDepartDate().getDepartDate()));
            trainTicketDto.setArrivalDate(format.format(ticketEntity.getArrivalDate().getDepartDate()));
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

}
