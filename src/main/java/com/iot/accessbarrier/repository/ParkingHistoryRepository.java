package com.iot.accessbarrier.repository;

import com.iot.accessbarrier.domain.ParkingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingHistoryRepository extends JpaRepository<ParkingHistory, Long> {
}
