package com.rahulshettyacademy.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.rahulshettyacademy.component.GreetingComponent;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Greeting API's",
        description = "We have the following API: Create user, update user, delete user, read user details"
)
@RestController
public class GreetingController {
    @Autowired
    GreetingComponent greetingComponent;

    AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public GreetingComponent greeting(@RequestParam(value = "name") String name) {
        greetingComponent.setId(counter.incrementAndGet());
        greetingComponent.setContent("Hey I am learning Spring Boot from " + name);
        return greetingComponent;
    }

}
