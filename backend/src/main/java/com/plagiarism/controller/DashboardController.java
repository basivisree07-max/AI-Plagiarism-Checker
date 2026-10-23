package com.plagiarism.controller;

import com.plagiarism.model.DashboardStats;
import com.plagiarism.service.DashboardService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getDashboardStats() {

        return ResponseEntity.ok(
                dashboardService.getDashboardStats()
        );
    }
}