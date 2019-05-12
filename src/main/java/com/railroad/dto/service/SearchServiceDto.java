package com.railroad.dto.service;


import lombok.Data;

import java.util.Date;

@Data
public class SearchServiceDto {
    private String departStation;
    private String arrivalStation;
    private String departDate;
    private String departReturnDate;
}
