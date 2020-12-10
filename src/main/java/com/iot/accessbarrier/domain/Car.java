package com.iot.accessbarrier.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 10, unique = true, nullable = false)
    private String plateNumber;
    private String brand;
    private String owner;
    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ParkingHistory> parkingHistories = new ArrayList<>();

    public void addParkingHistory(ParkingHistory parkingHistory) {
        parkingHistories.add(parkingHistory);
        parkingHistory.setCar(this);
    }
}
