package com.iot.accessbarrier.repository;

import com.iot.accessbarrier.domain.ParkingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingHistoryRepository extends JpaRepository<ParkingHistory, Long> {
    ParkingHistory getByCarIdAndDateToIsNull(long carId);
    List<ParkingHistory> getAllByCarPlateNumber(String plateNumber);
}
