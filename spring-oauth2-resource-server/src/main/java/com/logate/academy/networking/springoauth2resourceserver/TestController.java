package com.logate.academy.networking.springoauth2resourceserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @GetMapping("/private/hello")
    public String privateHello()
    {

        return "Hello, private!";
    }
}
