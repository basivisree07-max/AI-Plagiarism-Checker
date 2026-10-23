package com.plagiarism.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "similarity_results")
public class SimilarityResult {

    @Id
    private String id;

    private String assignmentId;

    private String submission1Id;

    private String submission2Id;

    private double tokenSimilarity;

    private double jaccardSimilarity;

    private double cosineSimilarity;

    private double structuralSimilarity;

    private double finalSimilarity;

    private String riskLevel;

    private LocalDateTime createdAt;

    public SimilarityResult() {
    }

    public SimilarityResult(
            String assignmentId,
            String submission1Id,
            String submission2Id,
            double tokenSimilarity,
            double jaccardSimilarity,
            double cosineSimilarity,
            double structuralSimilarity,
            double finalSimilarity,
            String riskLevel,
            LocalDateTime createdAt) {

        this.assignmentId = assignmentId;
        this.submission1Id = submission1Id;
        this.submission2Id = submission2Id;
        this.tokenSimilarity = tokenSimilarity;
        this.jaccardSimilarity = jaccardSimilarity;
        this.cosineSimilarity = cosineSimilarity;
        this.structuralSimilarity = structuralSimilarity;
        this.finalSimilarity = finalSimilarity;
        this.riskLevel = riskLevel;
        this.createdAt = createdAt;
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

    public String getSubmission1Id() {
        return submission1Id;
    }

    public void setSubmission1Id(String submission1Id) {
        this.submission1Id = submission1Id;
    }

    public String getSubmission2Id() {
        return submission2Id;
    }

    public void setSubmission2Id(String submission2Id) {
        this.submission2Id = submission2Id;
    }

    public double getTokenSimilarity() {
        return tokenSimilarity;
    }

    public void setTokenSimilarity(double tokenSimilarity) {
        this.tokenSimilarity = tokenSimilarity;
    }

    public double getJaccardSimilarity() {
        return jaccardSimilarity;
    }

    public void setJaccardSimilarity(double jaccardSimilarity) {
        this.jaccardSimilarity = jaccardSimilarity;
    }

    public double getCosineSimilarity() {
        return cosineSimilarity;
    }

    public void setCosineSimilarity(double cosineSimilarity) {
        this.cosineSimilarity = cosineSimilarity;
    }

    public double getStructuralSimilarity() {
        return structuralSimilarity;
    }

    public void setStructuralSimilarity(double structuralSimilarity) {
        this.structuralSimilarity = structuralSimilarity;
    }

    public double getFinalSimilarity() {
        return finalSimilarity;
    }

    public void setFinalSimilarity(double finalSimilarity) {
        this.finalSimilarity = finalSimilarity;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}