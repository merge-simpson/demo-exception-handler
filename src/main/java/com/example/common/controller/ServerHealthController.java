package com.example.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class ServerHealthController {
    @GetMapping("/health-check")
    public String health200() {
        return STR."HEALTH CHECK 200 OK: I'm fine at \{Instant.now()}";
    }
}
