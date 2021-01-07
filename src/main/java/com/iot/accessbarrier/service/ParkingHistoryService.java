package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.ParkingHistory;

import java.util.List;

public interface ParkingHistoryService {

    ParkingHistory save (ParkingHistory parkingHistory);

    List<ParkingHistory> getAll();

    ParkingHistory getByCarIdAndDateToIsNull(long carId);

    List<ParkingHistory> getAllByCarPlateNumber(String plateNumber);
}
