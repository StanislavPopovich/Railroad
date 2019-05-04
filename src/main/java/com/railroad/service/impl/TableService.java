package com.railroad.service.impl;

import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TableService {
    @Autowired
    private StationService stationService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostConstruct
    private void initStations(){
        amqpTemplate.convertAndSend("railroad.stations",getMessageStationBody("init"));
        amqpTemplate.convertAndSend("railroad.schedule",getScheduleMessageBody("init"));
    }

    public void updateStations(){
        amqpTemplate.convertAndSend("railroad.stations",getMessageStationBody("updateStation"));
    }

    public void updateSchedule(){
        amqpTemplate.convertAndSend("railroad.schedule",getScheduleMessageBody("updateSchedule"));
    }

    private String getMessageStationBody(String method){
        List<String> stations = stationService.getAllStationsName();
        StringBuilder result = new StringBuilder();
        result.append(method + "/");
        for(int i = 0; i < stations.size(); i++){
            if(i != stations.size() - 1){
                result.append(stations.get(i) + ",");
            }else{
                result.append(stations.get(i));
            }
        }
        return result.toString();
    }

    private String getScheduleMessageBody(String method){
        List<ScheduleMessageInfoDto> schedule = businessService.getActualSchedule();
        StringBuilder result = new StringBuilder();
        result.append(method + "/");
        for(int i = 0; i < schedule.size(); i++){
            ScheduleMessageInfoDto scheduleInfoDto = schedule.get(i);
            String departStation = scheduleInfoDto.getStations().get(0);
            String arrivalStation = scheduleInfoDto.getStations().get(scheduleInfoDto.getStations().size() - 1);
            if(i != schedule.size() - 1){
                result.append(scheduleInfoDto.getStation() + "," + scheduleInfoDto.getTrain() + ","
                + departStation + "," + arrivalStation + "," + scheduleInfoDto.getArrivalDate() + ","
                + scheduleInfoDto.getDepartDate() + "/");
            }else{
                result.append(scheduleInfoDto.getStation() + "," + scheduleInfoDto.getTrain() + ","
                        + departStation + "," + arrivalStation + "," + scheduleInfoDto.getArrivalDate() + ","
                        + scheduleInfoDto.getDepartDate());
            }
        }
        return result.toString();
    }
}
