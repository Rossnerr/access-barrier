package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.exception.EntityNotFoundException;
import com.iot.accessbarrier.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final LicensePlateRecognition licensePlateRecognition;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car getCarByImage(MultipartFile image) throws IOException {
        var carInfo = licensePlateRecognition.recognizePlateNumber(image);
        return carRepository.findByPlateNumber(carInfo.getPlateNumber())
                .orElseThrow(() -> new EntityNotFoundException("Car not found by plateNumber: " + carInfo.getPlateNumber()));
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }
}
