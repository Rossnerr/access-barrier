package com.iot.accessbarrier.dto.alpr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RecognizeLicensePlateDTO {
    private List<RLPResult> results;
    private int credits_monthly_total;
    private int credits_monthly_used;
}
