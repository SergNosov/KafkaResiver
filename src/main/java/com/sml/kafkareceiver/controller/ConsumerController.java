package com.sml.kafkareceiver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @GetMapping
    public void control(){
        System.out.println("--- start control");
    }
}
