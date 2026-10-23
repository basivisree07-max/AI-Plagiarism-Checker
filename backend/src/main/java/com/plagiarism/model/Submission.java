package com.plagiarism.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "submissions")
public class Submission {

    @Id
    private String id;

    private String assignmentId;
    private String studentId;
    private String fileName;
    private String sourceCode;
    private String language;
    private String status;
    private LocalDateTime submittedAt;

    public Submission() {
    }

    public Submission(String assignmentId,
                      String studentId,
                      String fileName,
                      String sourceCode,
                      String language,
                      String status,
                      LocalDateTime submittedAt) {

        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.fileName = fileName;
        this.sourceCode = sourceCode;
        this.language = language;
        this.status = status;
        this.submittedAt = submittedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}