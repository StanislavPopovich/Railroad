package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

/**
 * Data transfer object that represent schedule for train
 *
 * @author Stanislav Popovich
 */

@Data
public class ScheduleTrainDto {
    private Integer number;
    private List<String> stations;
    private List<String> arrivalDates;
    private List<String> departDates;
}
