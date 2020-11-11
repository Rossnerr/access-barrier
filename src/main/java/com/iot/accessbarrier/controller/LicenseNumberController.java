package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LicenseNumberController {

    private final CarService carService;

    @GetMapping("/index")
    public String index() {
        return "hi there!";
    }

    @PostMapping("/car")
    public ResponseEntity<Car> save(Car car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
