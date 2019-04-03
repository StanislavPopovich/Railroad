package com.railroad.service.api;
import com.railroad.dto.*;

import java.util.Date;
import java.util.List;

public interface BusinessService {
    /**
     * The method sends trainDto to train service layer
     * @param trainDto
     */
    void saveTrain(TrainDto trainDto);

    /**
     * The method sends stationDto to station service layer
     * @param stationDto
     */
    void saveStation(StationDto stationDto);

    /**
     * The method sends wayDto to way service layer
     * @param wayDto
     */
    void saveWay(WayDto wayDto);

    /**
     * The method sends userDto to user service layer
     * @param userDto
     */
    void saveUser(UserDto userDto);

    /**
     * The method sends scheduleDto to schedule service layer
     * @param scheduleDto
     */
    void saveSchedule(ScheduleDto scheduleDto);

    //Надо переписать
    void saveTicketAndPassenger(PassengerDto passengerDto);

    /**
     * The method returns all stations names from station service layer
     * @return list of Strings
     */
    List<String> getAllNamesStations();

    /**
     * The method returns all trainDtos from train service layer
     * @return list of TrainDto
     */
    List<TrainDto> getAllTrains();

    /**
     * The method returns direct trains from starting station to destination station on a specific date
     * @param startStation
     * @param destStation
     * @param date
     * @return list of trainDto
     */
    List<TrainDto> getDirectTrains(String startStation, String destStation, Date date);

    /**
     * The method returns all roleDtos from role service layer
     * @return list of RoleDto
     */
    List<String> getRolesNames();

    /**
     * The methos returns all wayDtos from way service layer
     * @return list of WayDto
     */
    List<WayDto> getAllWay();

    /**
     * The method returns all userDtos from user service layer
     * @return list of UserDto
     */
    List<UserDto> getAllUsers();

    /**
     * The method returns all routes from starting station to destination station
     * @param startStation
     * @param endStation
     * @return list of Strings
     */
    List<String> getAllRoutes(String startStation, String endStation);

    /**
     * The method returns userDto by user name from user service layer
     * @param userName
     * @return UserDto
     */
    UserDto getUserByUserName(String userName);

    /**
     * The method sends userDto to user service layer
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * The method sends userDto to user service layer
     * @param userName
     */
    void deleteUser(String userName);




}
