package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.exception.EntityNotFoundException;
import com.iot.accessbarrier.exception.NotUniqueException;
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
        validatePlateNumber(car.getPlateNumber());
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber)
                .orElseThrow(() -> new EntityNotFoundException("Car not found by plateNumber: " + plateNumber));
    }

    @Override
    public Car getCarByImage(MultipartFile image) throws IOException {
        var carInfo = licensePlateRecognition.recognizePlateNumber(image);
        return getCarByPlateNumber(carInfo.getPlateNumber());
    }

    private void validatePlateNumber(String plateNumber) {
        if (carRepository.findByPlateNumber(plateNumber).isPresent()) {
            throw new NotUniqueException("There is already a car with this plateNumber: " + plateNumber);
        }
    }

}
