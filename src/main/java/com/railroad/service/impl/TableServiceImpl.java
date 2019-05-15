package com.railroad.service.impl;

import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.TableService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private StationService stationService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * Sending init data to scoreboard
     */
    @PostConstruct
    private void initStations() throws RailroadDaoException {
        amqpTemplate.convertAndSend("railroad.stations",getMessageStationBody("init"));
        amqpTemplate.convertAndSend("railroad.schedule",getScheduleMessageBody("init"));
    }

    /**
     * Sending updated data of stations
     */
    @Override
    public void updateStations() throws RailroadDaoException {
        amqpTemplate.convertAndSend("railroad.stations",getMessageStationBody("updateStation"));
    }

    /**
     * Sending updated data of schedule
     */
    @Override
    public void updateSchedule() throws RailroadDaoException {
        amqpTemplate.convertAndSend("railroad.schedule",getScheduleMessageBody("updateSchedule"));
    }

    /**
     * Creating body of message for stations
     * @param method name of method
     * @return String
     */
    private String getMessageStationBody(String method) throws RailroadDaoException {
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

    /**
     * Creating body of message for schedule
     * @param method name of method
     * @return String
     */
    private String getScheduleMessageBody(String method) throws RailroadDaoException {
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
