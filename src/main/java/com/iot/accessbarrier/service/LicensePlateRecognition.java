package com.iot.accessbarrier.service;

import com.iot.accessbarrier.dto.CarInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LicensePlateRecognition {

    CarInfoDto recognizePlateNumber(MultipartFile image) throws IOException;
}
