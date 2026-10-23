package com.plagiarism.controller;

import com.plagiarism.model.Report;
import com.plagiarism.service.ReportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Generate a report for an assignment
    @PostMapping("/{assignmentId}")
    public ResponseEntity<Report> generateReport(
            @PathVariable String assignmentId) {

        Report report =
                reportService.generateReport(assignmentId);

        return ResponseEntity.ok(report);
    }

    // Get an existing report
    @GetMapping("/{assignmentId}")
    public ResponseEntity<Report> getReport(
            @PathVariable String assignmentId) {

        Report report =
                reportService.getReport(assignmentId);

        return ResponseEntity.ok(report);
    }
}