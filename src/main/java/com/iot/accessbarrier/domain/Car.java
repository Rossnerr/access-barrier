package com.iot.accessbarrier.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String plateNumber;
    private String brand;
    private String owner;
}
