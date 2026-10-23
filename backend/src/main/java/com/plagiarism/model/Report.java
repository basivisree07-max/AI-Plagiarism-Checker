package com.plagiarism.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reports")
public class Report {

    @Id
    private String id;

    private String assignmentId;
    private int totalSubmissions;
    private int highRiskPairs;
    private int mediumRiskPairs;
    private int lowRiskPairs;
    private double averageSimilarity;
    private LocalDateTime generatedAt;

    public Report() {
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

    public int getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(int totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public int getHighRiskPairs() {
        return highRiskPairs;
    }

    public void setHighRiskPairs(int highRiskPairs) {
        this.highRiskPairs = highRiskPairs;
    }

    public int getMediumRiskPairs() {
        return mediumRiskPairs;
    }

    public void setMediumRiskPairs(int mediumRiskPairs) {
        this.mediumRiskPairs = mediumRiskPairs;
    }

    public int getLowRiskPairs() {
        return lowRiskPairs;
    }

    public void setLowRiskPairs(int lowRiskPairs) {
        this.lowRiskPairs = lowRiskPairs;
    }

    public double getAverageSimilarity() {
        return averageSimilarity;
    }

    public void setAverageSimilarity(double averageSimilarity) {
        this.averageSimilarity = averageSimilarity;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}