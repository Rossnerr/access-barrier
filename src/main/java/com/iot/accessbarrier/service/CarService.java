package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    Car save(Car car);

    Car saveBasedOnImage(MultipartFile image) throws IOException;

    void deleteById(Long id);

    List<Car> getAll();

    Car getCarByPlateNumber(String plateNumber);

    Car getCarByImage(MultipartFile image) throws IOException;

    List<Car> getAllCarsInParkingArea();
}
