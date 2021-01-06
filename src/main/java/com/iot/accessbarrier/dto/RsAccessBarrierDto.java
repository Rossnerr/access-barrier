package com.iot.accessbarrier.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RsAccessBarrierDto {
    public Long parkingHistoryId;
    public ParkingAction action;
    public Date enteredTime;
    public Date exitTime;
    public RsCarDTO car;
}
