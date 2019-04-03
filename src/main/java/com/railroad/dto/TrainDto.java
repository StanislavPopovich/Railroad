package com.railroad.dto;

import lombok.Data;

import java.util.Date;
import java.util.LinkedList;

@Data
public class TrainDto {
    private Integer number;
    private Integer seats;
    private LinkedList<String> stations;
    private String departDate;

}
