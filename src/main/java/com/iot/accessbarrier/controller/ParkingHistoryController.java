package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.domain.ParkingHistory;
import com.iot.accessbarrier.service.ParkingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingHistoryController {

    private final ParkingHistoryService parkingHistoryService;

    @GetMapping("/parking-history")
    public ResponseEntity<List<ParkingHistory>> getAll() {
        return ResponseEntity.ok(parkingHistoryService.getAll());
    }

}
