package com.railroad.mapper;

import com.railroad.dto.ScheduleDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.StationEntity;
import com.railroad.model.TrainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleEntityDtoMapper {

    default ScheduleEntity scheduleDtoToScheduleEntity(ScheduleDto scheduleDto){
        if (scheduleDto == null) {
            return null;
        } else {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setDepartDate(getDate(scheduleDto.getDepartDate(), "yyyy-MM-dd HH:mm"));
            scheduleEntity.setArrivalDate(getDate(scheduleDto.getArrivalDate(), "yyyy-MM-dd HH:mm"));
            scheduleEntity.setDepartDateFromFirstStation(getDate(scheduleDto.
                    getDepartDateFromFirstStation(), "yyyy-MM-dd"));
            return scheduleEntity;
        }
    }

    default ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity){
        if (scheduleEntity == null) {
            return null;
        } else {
            ScheduleDto scheduleDto = new ScheduleDto();
            scheduleDto.setDepartDate(scheduleEntity.getDepartDate().toString());
            scheduleDto.setArrivalDate(scheduleEntity.getArrivalDate().toString());
            scheduleDto.setDepartDateFromFirstStation(scheduleEntity.getDepartDateFromFirstStation().toString());
            scheduleDto.setStationName(scheduleEntity.getStationEntity().getName());
            scheduleDto.setTrainNumber(scheduleEntity.getTrainEntity().getNumber());
            return scheduleDto;
        }
    };


    List<ScheduleDto> scheduleEntitiesToScheduleDtos(List<ScheduleEntity> scheduleEntities);
    List<ScheduleEntity> scheduleDtosToScheduleEntities(List<ScheduleDto> scheduleDtos);

    default Date getDate(String date, String dateFormat){
        SimpleDateFormat format;
        if(dateFormat.equals("yyyy-MM-dd HH:mm")){
            format = new SimpleDateFormat(dateFormat);
        }else{
            format = new SimpleDateFormat(dateFormat);
        }
        Date resDate = null;
        try {
            resDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resDate;
    }




}
