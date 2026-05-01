package com.bharti.stripe_provider_service.controller;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {

    @Value("${mytestkey}")
    private String myTestKey;

    private Logger logger = LoggerFactory.getLogger(AdditionController.class);

    @GetMapping("/add")
    public int add(@RequestParam int val1, @RequestParam int val2) {
        logger.info("val1:{}|val2:{}", val1, val2);

        int sumResult = val1 + val2;
        logger.info("sumResult:{}", sumResult);

        return sumResult;
    }

    @PostConstruct
    public void init(){
        logger.info("*******myTestKey:{}", myTestKey);
    }
}