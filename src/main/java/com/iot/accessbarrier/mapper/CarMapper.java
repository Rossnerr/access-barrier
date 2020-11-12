package com.iot.accessbarrier.mapper;

import com.iot.accessbarrier.domain.Car;
import com.iot.accessbarrier.dto.RqCarDTO;
import com.iot.accessbarrier.dto.RsCarDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car rqCarDTOtoCar(RqCarDTO rqCarDTO);
    RsCarDTO carToRsCarDTO(Car car);
}
