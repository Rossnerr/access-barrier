package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.dto.RqCarDTO;
import com.iot.accessbarrier.dto.RsCarDTO;
import com.iot.accessbarrier.mapper.CarMapper;
import com.iot.accessbarrier.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<RsCarDTO> save(@RequestBody RqCarDTO rqCarDTO) {
        var car = CarMapper.INSTANCE.rqCarDTOtoCar(rqCarDTO);
        var rsCarDTO = CarMapper.INSTANCE.carToRsCarDTO(carService.save(car));
        return ResponseEntity.ok(rsCarDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsCarDTO>> getAll() {
        var rsCarDTOs = carService.getAll().stream()
                .map(CarMapper.INSTANCE::carToRsCarDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rsCarDTOs);
    }


    @PostMapping("/image")
    public ResponseEntity<RsCarDTO> getByImage(@RequestParam(value = "image") MultipartFile image) throws IOException {
        var rsCarDTO = CarMapper.INSTANCE.carToRsCarDTO(carService.getCarByImage(image));
        return ResponseEntity.ok(rsCarDTO);
    }

    @GetMapping("{plateNumber}")
    public ResponseEntity<RsCarDTO> getByPlateNumber(@PathVariable String plateNumber) {
        var rsCarDTO = CarMapper.INSTANCE.carToRsCarDTO(carService.getCarByPlateNumber(plateNumber));
        return ResponseEntity.ok(rsCarDTO);
    }


}
