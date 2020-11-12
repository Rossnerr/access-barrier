package com.iot.accessbarrier.dto.alpr;

import lombok.Data;

@Data
public class RLPResult {
    private String plate;
    private String confidence;
    private Vehicle vehicle;
}
