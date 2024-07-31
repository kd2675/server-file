package com.example.file.api.health.act;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/health")
public class HealthController {
    @GetMapping("/check")
    public String health() {
        return "ok";
    }
}
