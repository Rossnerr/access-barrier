package com.iot.accessbarrier.service;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.domain.ParkingHistory;
import com.iot.accessbarrier.dto.ParkingAction;
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
    public RsAccessBarrierDto raiseBarrier(MultipartFile image) throws IOException {
        var car = carService.getCarByImage(image);
        var parkingHistory = parkingHistoryService.getByCarIdAndDateToIsNull(car.getId());
        ParkingAction action;

        if (parkingHistory == null) {
            action = ParkingAction.ENTER;
            parkingHistory = enterParking(car);
        } else {
            action = ParkingAction.EXIT;
            exitParking(parkingHistory);
        }

        return createResponse(car, parkingHistory, action);
    }

    private ParkingHistory enterParking(Car car) {
        var parkingHistory = new ParkingHistory();
        parkingHistory.setDateFrom(new Date());
        car.addParkingHistory(parkingHistory);

        return parkingHistoryService.save(parkingHistory);
    }

    private void exitParking(ParkingHistory parkingHistory) {
        parkingHistory.setDateTo(new Date());
        parkingHistoryService.save(parkingHistory);
    }

    private RsAccessBarrierDto createResponse(Car car, ParkingHistory parkingHistory, ParkingAction action) {
        return RsAccessBarrierDto.builder()
                .parkingHistoryId(parkingHistory.getId())
                .action(action)
                .enteredTime(parkingHistory.getDateFrom())
                .exitTime(parkingHistory.getDateTo())
                .car(CarMapper.INSTANCE.carToRsCarDTO(car))
                .build();
    }
}
