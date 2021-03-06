package com.railroad.dto.passenger;

import lombok.Data;

@Data
public class PassengerUpdateDto {
    private String lastName;
    private String name;
    private String birthDate;
    private String oldLastName;
    private String oldName;
    private String oldBirthDate;
}
