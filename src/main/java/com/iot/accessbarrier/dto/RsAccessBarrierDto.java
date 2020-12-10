package com.iot.accessbarrier.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RsAccessBarrierDto {
    public Long parkingHistoryId;
    public Date enteredTime;
    public RsCarDTO car;
}
