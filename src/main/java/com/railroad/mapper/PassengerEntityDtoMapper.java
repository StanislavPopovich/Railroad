package com.railroad.mapper;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.passenger.PassengerUpdateDto;
import com.railroad.model.PassengerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PassengerEntityDtoMapper {

    @Mapping(source ="birthDate", dateFormat = "yyyy-MM-dd",target = "birthDate")
    PassengerEntity passengerDtoToEntity(PassengerDto passengerDto);

    @Mapping(source ="birthDate", dateFormat = "yyyy-MM-dd",target = "birthDate")
    PassengerDto passengerEntityToDto(PassengerEntity passengerEntity);

    List<PassengerDto> passengerEntitiesToPassengerDtos(Set<PassengerEntity> passengerEntities);
    List<PassengerDto> passengerEntitiesToPassengerDtos(List<PassengerEntity> passengerEntities);

    @Mapping(source ="oldBirthDate", dateFormat = "yyyy-MM-dd",target = "birthDate")
    @Mapping(source ="oldLastName",target = "lastName")
    @Mapping(source ="oldName", target = "name")
    PassengerEntity passengerUpdateDtoToOldEntity(PassengerUpdateDto passengerUpdateDto);

    @Mapping(source ="birthDate", dateFormat = "yyyy-MM-dd",target = "birthDate")
    PassengerEntity passengerUpdateDtoToEntity(PassengerUpdateDto passengerUpdateDto);
}
