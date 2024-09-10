package com.ai.devs.ai_devs.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${api.key}")
    String key;

    @GetMapping("/hello")
    public String sayHello() {
        return String.format("The key is: %s!", key);
    }
}
