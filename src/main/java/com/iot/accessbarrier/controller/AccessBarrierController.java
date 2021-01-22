package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.dto.RsAccessBarrierDto;
import com.iot.accessbarrier.service.AccessBarrierService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/access-barrier")
@RequiredArgsConstructor
public class AccessBarrierController {

    private final AccessBarrierService accessBarrierService;

    @ApiOperation(value = "Raises up the barrier if car from the provided image has access to the parking area")
    @PostMapping
    public ResponseEntity<RsAccessBarrierDto> raiseBarrier(@RequestParam(value = "image") MultipartFile image) throws IOException {
        log.info("New attempt to pass the barrier: " + LocalDateTime.now());
        var response = accessBarrierService.raiseBarrier(image);
        log.info("Car plate number was recognized: " + response.car.getPlateNumber());
        return ResponseEntity.ok(response);
    }
}
