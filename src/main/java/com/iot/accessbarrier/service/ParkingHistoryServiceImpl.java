package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.ParkingHistory;
import com.iot.accessbarrier.repository.ParkingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingHistoryServiceImpl implements ParkingHistoryService {

    private final ParkingHistoryRepository parkingHistoryRepository;

    @Override
    public ParkingHistory save(ParkingHistory parkingHistory) {
        return parkingHistoryRepository.save(parkingHistory);
    }

    @Override
    public List<ParkingHistory> getAll() {
        return parkingHistoryRepository.findAll();
    }

    @Override
    public ParkingHistory getByCarIdAndDateToIsNull(long carId) {
        return parkingHistoryRepository.getByCarIdAndDateToIsNull(carId);
    }

    @Override
    public List<ParkingHistory> getAllByCarPlateNumber(String plateNumber) {
        return parkingHistoryRepository.getAllByCarPlateNumber(plateNumber);
    }
}
