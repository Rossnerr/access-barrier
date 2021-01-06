package com.iot.accessbarrier.service;

import com.iot.accessbarrier.dto.RsAccessBarrierDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AccessBarrierService {

    RsAccessBarrierDto raiseBarrier(MultipartFile image) throws IOException;
}
