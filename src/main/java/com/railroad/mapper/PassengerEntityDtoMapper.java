package com.railroad.mapper;

import com.railroad.dto.PassengerDto;
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

}
