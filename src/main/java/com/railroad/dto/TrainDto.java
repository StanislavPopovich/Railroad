package com.railroad.dto;

import lombok.Data;
import java.util.LinkedList;

@Data
public class TrainDto {
    private Integer number;
    private Integer seats;
    private LinkedList<String> stations;

}
