package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.dto.CarInfoDto;
import com.iot.accessbarrier.dto.RqCarDTO;
import com.iot.accessbarrier.dto.RsCarDTO;
import com.iot.accessbarrier.mapper.CarMapper;
import com.iot.accessbarrier.service.CarService;
import com.iot.accessbarrier.service.LicensePlateRecognition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LicenseNumberController {

    private final CarService carService;
    private final LicensePlateRecognition licensePlateRecognition;

    @PostMapping("/car")
    public ResponseEntity<RsCarDTO> save(@RequestBody RqCarDTO rqCarDTO) {
        var car = CarMapper.INSTANCE.rqCarDTOtoCar(rqCarDTO);
        var rsCarDTO = CarMapper.INSTANCE.carToRsCarDTO(carService.save(car));
        return ResponseEntity.ok(rsCarDTO);
    }

    @PostMapping("/car/image/check")
    public ResponseEntity<RsCarDTO> getCarByImage(@RequestParam(value = "image") MultipartFile image) throws IOException {
        var rsCarDTO = CarMapper.INSTANCE.carToRsCarDTO(carService.getCarByImage(image));
        return ResponseEntity.ok(rsCarDTO);
    }

    @PostMapping("/car/recognize")
    public ResponseEntity<CarInfoDto> getCarInfo(@RequestParam(value = "image") MultipartFile image) throws IOException {
        var response = licensePlateRecognition.recognizePlateNumber(image);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/car/{id}")
//    public ResponseEntity<RsCarDTO> getCarById(@PathVariable Long id) {
//        var rsCarDTO = CarMapper.INSTANCE.carToRsCarDTO(carService.getById(id));
//        return ResponseEntity.ok(rsCarDTO);
//    }

    @GetMapping("/car")
    public ResponseEntity<List<RsCarDTO>> getAll() {
        var rsCarDTOs = carService.getAll().stream()
                .map(CarMapper.INSTANCE::carToRsCarDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rsCarDTOs);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
