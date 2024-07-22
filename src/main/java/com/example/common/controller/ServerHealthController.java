package com.example.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerHealthController {
    @GetMapping("/health-check")
    public String health200() {
        return "FINE THANKS AND YOU?";
    }
}
