package com.railroad.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerDto {
    private String lastName;
    private String name;
    private String birthDate;

    public PassengerDto(){}
    public PassengerDto(String lastName, String name, String birthDate){
        this.lastName = lastName;
        this.name = name;
        this.birthDate = birthDate;
    }
}
