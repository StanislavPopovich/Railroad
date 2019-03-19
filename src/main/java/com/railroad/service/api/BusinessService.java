package com.railroad.service.api;
import com.railroad.dto.*;

import java.util.List;

public interface BusinessService {
    void saveTrain(TrainDto trainDto);
    void saveStation(StationDto stationDto);
    void saveWay(WayDto wayDto);
    void saveUser(UserDto userDto);
    List<String> getAllStations();
    List<TrainDto> getAllTrains();
    List<RoleDto> getAllRoles();
    List<WayDto> getAllWay();
    List<UserDto> getAllUsers();
    List<List<String>> getAllRoutes(String startStation, String endStation);
    UserDto getUserByUserName(String userName);
    void updateUser(UserDto userDto);

}
