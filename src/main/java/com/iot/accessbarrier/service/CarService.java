package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CarService {

    Car save (Car car);
    Car getById(Long id);
    void deleteById(Long id);
    Car getCarByImage(MultipartFile image) throws IOException;
}
