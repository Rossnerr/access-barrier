package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.exception.EntityNotFoundException;
import com.iot.accessbarrier.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

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
}
