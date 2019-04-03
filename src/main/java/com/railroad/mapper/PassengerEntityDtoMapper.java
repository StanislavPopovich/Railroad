package com.railroad.mapper;

import com.railroad.dto.PassengerDto;
import com.railroad.model.PassengerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerEntityDtoMapper {

    PassengerEntity passengerDtoToEntity(PassengerDto passengerDto);
    PassengerDto passengerEntityToDto(PassengerEntity passengerEntity);
}
