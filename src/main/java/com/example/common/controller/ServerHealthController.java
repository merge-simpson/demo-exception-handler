package com.example.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerHealthController {
    @GetMapping("/")
    public String health200() {
        return "SERVER ACTIVE";
    }
}
