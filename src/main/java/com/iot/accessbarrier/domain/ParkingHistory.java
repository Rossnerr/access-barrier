package com.iot.accessbarrier.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "parking_histories")
public class ParkingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "car_id")
    private Car car;
}
