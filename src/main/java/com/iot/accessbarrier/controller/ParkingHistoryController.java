package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.dto.RsParkingHistoryDTO;
import com.iot.accessbarrier.mapper.ParkingHistoryMapper;
import com.iot.accessbarrier.service.ParkingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parking-history")
@RequiredArgsConstructor
public class ParkingHistoryController {

    private final ParkingHistoryService parkingHistoryService;

    @GetMapping
    public ResponseEntity<List<RsParkingHistoryDTO>> getAll() {
        var parkingHistories = parkingHistoryService.getAll().stream()
                .map(ParkingHistoryMapper.INSTANCE::parkingHistoryToRsParkingHistoryDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parkingHistories);
    }

    @GetMapping("/{plateNumber}")
    public ResponseEntity<List<RsParkingHistoryDTO>> getAllByPlateNumber(@PathVariable String plateNumber) {
        var parkingHistories = parkingHistoryService.getAllByCarPlateNumber(plateNumber).stream()
                .map(ParkingHistoryMapper.INSTANCE::parkingHistoryToRsParkingHistoryDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parkingHistories);
    }

}
