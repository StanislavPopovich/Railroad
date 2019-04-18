package com.railroad.mapper;

import com.railroad.dto.PassengerDto;
import com.railroad.model.PassengerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@Mapper(componentModel = "spring")
public interface PassengerEntityDtoMapper {

    PassengerEntity passengerDtoToEntity(PassengerDto passengerDto);

    @Mapping(source ="birthDate", dateFormat = "yyyy-MM-dd",target = "birthDate")
    PassengerDto passengerEntityToDto(PassengerEntity passengerEntity);

    List<PassengerDto> passengerEntitiesToPassengerDtos(Set<PassengerEntity> passengerEntities);


    default Date stringToDate(String date){
        if(date !=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date birthDate = null;
            try {
                birthDate = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return birthDate;
        }
        return null;
    }
}
