package com.railroad.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;

@Getter
@Setter
@ToString
public class TrainTicketDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
    private LinkedList<String> stations;
}
