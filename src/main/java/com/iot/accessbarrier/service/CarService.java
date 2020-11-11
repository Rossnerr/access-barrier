package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;

public interface CarService {

    Car save (Car car);
    Car getById(Long id);
    void deleteById(Long id);
}
