package com.railroad.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrainSearchDto {
    private Integer number;
    private Integer seats;
    private String departDate;
    private String arrivalDate;
}
