package com.example.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerHealthController {
    @GetMapping("/")
    public String health200() {
        return "SERVER ACTIVE";
    }
}
