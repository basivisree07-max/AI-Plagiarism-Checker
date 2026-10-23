package com.plagiarism.service;

import com.plagiarism.model.AuditLog;
import com.plagiarism.repository.AuditLogRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(
            AuditLogRepository auditLogRepository) {

        this.auditLogRepository = auditLogRepository;
    }

    // Create a new audit log
    public AuditLog createLog(
            String userId,
            String action,
            String assignmentId) {

        AuditLog log = new AuditLog();

        log.setUserId(userId);

        log.setAction(action);

        log.setAssignmentId(assignmentId);

        log.setTimestamp(LocalDateTime.now());

        return auditLogRepository.save(log);
    }

    // Get logs of a particular user
    public List<AuditLog> getUserLogs(
            String userId) {

        return auditLogRepository
                .findByUserId(userId);
    }

    // Get logs related to an assignment
    public List<AuditLog> getAssignmentLogs(
            String assignmentId) {

        return auditLogRepository
                .findByAssignmentId(assignmentId);
    }

    // Get all audit logs
    public List<AuditLog> getAllLogs() {

        return auditLogRepository.findAll();
    }
}