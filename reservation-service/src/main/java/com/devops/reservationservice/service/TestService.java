package com.devops.reservationservice.service;

import com.devops.reservationservice.model.Admin;
import com.devops.reservationservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

//    @Autowired
//    TestRepository testRepository;

    public String test(){
//        System.out.println("uslo u testt");
//        try {
//            testRepository.save(new Admin(1L, "pera"));
//        }catch (Error message){
//            System.out.println(message);
//        }
        System.out.println();
        return "Welcome from reservation-service";
    }

    public String testService(){
        return "Welcome from reservation-service";
    }
}

