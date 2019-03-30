package com.railroad.service.api;
import com.railroad.dto.*;

import java.util.List;

public interface BusinessService {
    void saveTrain(TrainDto trainDto);
    void saveStation(StationDto stationDto);
    void saveWay(WayDto wayDto);
    void saveUser(UserDto userDto);
    void saveSchedule(ScheduleDto scheduleDto);
    List<String> getAllNamesStations();
    List<TrainDto> getAllTrains();
    List<TrainDto> getDirectTrains(String startStation, String destStation);
    void getTransferTrains(String startStation, String destStation);
    List<RoleDto> getAllRoles();
    List<WayDto> getAllWay();
    List<UserDto> getAllUsers();
    List<StationDto> getAllStations();
    List<String> getAllRoutes(String startStation, String endStation);
    UserDto getUserByUserName(String userName);
    void updateUser(UserDto userDto);

    List<ScheduleDto> getSchedulesByTrainNumber(Integer trainNumber);
    List<ScheduleDto> getSchedulesByStationName(String stationName);

}
