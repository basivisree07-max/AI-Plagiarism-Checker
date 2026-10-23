package com.plagiarism.repository;

import com.plagiarism.model.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditLogRepository
        extends MongoRepository<AuditLog, String> {

    List<AuditLog> findByUserId(String userId);

    List<AuditLog> findByAssignmentId(String assignmentId);
}