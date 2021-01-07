package com.iot.accessbarrier.repository;

import com.iot.accessbarrier.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByPlateNumber(String number);

    @Query("select c from Car c inner join ParkingHistory ph on c.id = ph.car.id where ph.dateTo is null")
    List<Car> getAllCarsInParkingArea();
}
