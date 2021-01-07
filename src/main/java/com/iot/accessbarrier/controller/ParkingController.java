package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.dto.RsCarDTO;
import com.iot.accessbarrier.mapper.CarMapper;
import com.iot.accessbarrier.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final CarService carService;

    @GetMapping("/cars-in-parking-area")
    public ResponseEntity<List<RsCarDTO>> getAllCarsInParkingArea() {
        var rsCarDTOs = carService.getAllCarsInParkingArea().stream()
                .map(CarMapper.INSTANCE::carToRsCarDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rsCarDTOs);
    }

}
