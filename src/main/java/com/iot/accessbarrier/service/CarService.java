package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.dto.RsCarDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    Car save (Car car);
    Car getById(Long id);
    void deleteById(Long id);
    Car getCarByImage(MultipartFile image) throws IOException;
    List<Car> getAll();
}
