package com.devops.reservationservice.controller;

import com.devops.reservationservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test(){
        testService.test();
        return "Welcome from reservation-service";
    }
}

