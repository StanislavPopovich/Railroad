package com.railroad.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerDto {
    private String lastName;
    private String name;
    private Date BirthDate;
    private String trainNumber;
    private String departDate;
    private String userName;
}