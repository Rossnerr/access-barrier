package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.dto.CarInfoDto;
import com.iot.accessbarrier.service.LicensePlateRecognition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LicenseNumberController {

    private final LicensePlateRecognition licensePlateRecognition;

    @PostMapping("/car/recognize")
    public ResponseEntity<CarInfoDto> getCarInfo(@RequestParam(value = "image") MultipartFile image) throws IOException {
        var response = licensePlateRecognition.recognizePlateNumber(image);
        return ResponseEntity.ok(response);
    }

}
