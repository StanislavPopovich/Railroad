package com.railroad.dto.service;


import lombok.Data;

/**
 * Data transfer object that represent data which user is inserting in system for searching trains
 *
 * @author Stanislav Popovich
 */

@Data
public class SearchServiceDto {
    private String departStation;
    private String arrivalStation;
    private String departDate;
    private String departReturnDate;
}
