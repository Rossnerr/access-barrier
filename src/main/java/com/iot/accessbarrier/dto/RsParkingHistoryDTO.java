package com.iot.accessbarrier.dto;

import com.iot.accessbarrier.domain.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RsParkingHistoryDTO {
    private Long id;
    private Date dateFrom;
    private Date dateTo;
}
