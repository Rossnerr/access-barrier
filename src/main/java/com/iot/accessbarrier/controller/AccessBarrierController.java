package com.iot.accessbarrier.controller;

import com.iot.accessbarrier.dto.RsAccessBarrierDto;
import com.iot.accessbarrier.service.AccessBarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/access-barrier")
@RequiredArgsConstructor
public class AccessBarrierController {

    private final AccessBarrierService accessBarrierService;

    @PostMapping("/enter")
    public ResponseEntity<RsAccessBarrierDto> enter(@RequestParam(value = "image") MultipartFile image) throws IOException {
        var response = accessBarrierService.enter(image);
        return ResponseEntity.ok(response);
    }
}
