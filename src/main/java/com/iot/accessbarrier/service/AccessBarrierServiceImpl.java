package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.domain.ParkingHistory;
import com.iot.accessbarrier.dto.RsAccessBarrierDto;
import com.iot.accessbarrier.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccessBarrierServiceImpl implements AccessBarrierService {

    private final CarService carService;
    private final ParkingHistoryService parkingHistoryService;

    @Override
    public RsAccessBarrierDto enter(MultipartFile image) throws IOException {
        var car = carService.getCarByImage(image);
        var parkingHistory = new ParkingHistory();

        parkingHistory.setDateFrom(new Date());
        car.addParkingHistory(parkingHistory);
        parkingHistoryService.save(parkingHistory);

        return createResponse(car, parkingHistory);
    }

    private RsAccessBarrierDto createResponse(Car car, ParkingHistory parkingHistory) {
        return RsAccessBarrierDto.builder()
                .parkingHistoryId(parkingHistory.getId())
                .enteredTime(parkingHistory.getDateFrom())
                .car(CarMapper.INSTANCE.carToRsCarDTO(car))
                .build();
    }
}
