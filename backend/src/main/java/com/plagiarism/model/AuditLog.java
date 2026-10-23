package com.plagiarism.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
public class AuditLog {

    @Id
    private String id;

    private String userId;
    private String action;
    private String assignmentId;
    private LocalDateTime timestamp;

    public AuditLog() {
    }

    public AuditLog(
            String userId,
            String action,
            String assignmentId,
            LocalDateTime timestamp) {

        this.userId = userId;
        this.action = action;
        this.assignmentId = assignmentId;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}