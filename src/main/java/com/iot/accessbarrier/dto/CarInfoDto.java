package com.iot.accessbarrier.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarInfoDto {
    private String plateNumber;
    private String brand;
    private int numberOfLeftRequests;

}
