package com.iot.accessbarrier.mapper;

import com.iot.accessbarrier.domain.ParkingHistory;
import com.iot.accessbarrier.dto.RsParkingHistoryDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ParkingHistoryMapper {
    ParkingHistoryMapper INSTANCE = Mappers.getMapper(ParkingHistoryMapper.class);

    RsParkingHistoryDTO parkingHistoryToRsParkingHistoryDTO(ParkingHistory parkingHistory);
}
