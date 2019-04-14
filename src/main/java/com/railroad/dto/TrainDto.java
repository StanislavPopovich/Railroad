package com.railroad.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.LinkedList;

@Getter
@Setter
public class TrainDto {
    private Integer number;
    private Integer seats;
    private LinkedList<String> stations;


}
