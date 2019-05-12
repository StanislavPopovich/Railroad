package com.railroad.dto.train;

import lombok.Data;
import javax.validation.constraints.Digits;
import java.util.LinkedList;

// This is dto for adding and view trains

@Data
public class TrainDto {

    @Digits(integer = 3, fraction = 0, message = "{train.number.error}")
    private Integer number;

    @Digits(integer = 3, fraction = 0, message = "{train.seats.error}")
    private Integer seats;

    private LinkedList<String> stations;


}
