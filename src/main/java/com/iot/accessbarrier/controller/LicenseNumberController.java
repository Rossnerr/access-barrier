package com.iot.accessbarrier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LicenseNumberController {

    @GetMapping
    public String index() {
        return "hi there!";
    }

}
