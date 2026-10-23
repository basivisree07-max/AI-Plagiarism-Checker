package com.plagiarism.controller;

import com.plagiarism.model.AuditLog;
import com.plagiarism.service.AuditLogService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(
            AuditLogService auditLogService) {

        this.auditLogService = auditLogService;
    }

    // Get all audit logs
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {

        return ResponseEntity.ok(
                auditLogService.getAllLogs()
        );
    }

    // Get logs for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLog>> getUserLogs(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                auditLogService.getUserLogs(userId)
        );
    }

    // Get logs for an assignment
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AuditLog>>
    getAssignmentLogs(
            @PathVariable String assignmentId) {

        return ResponseEntity.ok(
                auditLogService.getAssignmentLogs(
                        assignmentId
                )
        );
    }
}