package com.plagiarism.model;

public class DashboardStats {

    private long totalAssignments;
    private long totalSubmissions;
    private long totalSimilarityChecks;

    private long highRiskPairs;
    private long mediumRiskPairs;
    private long lowRiskPairs;

    private double averageSimilarity;

    public DashboardStats() {
    }

    public long getTotalAssignments() {
        return totalAssignments;
    }

    public void setTotalAssignments(long totalAssignments) {
        this.totalAssignments = totalAssignments;
    }

    public long getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(long totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public long getTotalSimilarityChecks() {
        return totalSimilarityChecks;
    }

    public void setTotalSimilarityChecks(long totalSimilarityChecks) {
        this.totalSimilarityChecks = totalSimilarityChecks;
    }

    public long getHighRiskPairs() {
        return highRiskPairs;
    }

    public void setHighRiskPairs(long highRiskPairs) {
        this.highRiskPairs = highRiskPairs;
    }

    public long getMediumRiskPairs() {
        return mediumRiskPairs;
    }

    public void setMediumRiskPairs(long mediumRiskPairs) {
        this.mediumRiskPairs = mediumRiskPairs;
    }

    public long getLowRiskPairs() {
        return lowRiskPairs;
    }

    public void setLowRiskPairs(long lowRiskPairs) {
        this.lowRiskPairs = lowRiskPairs;
    }

    public double getAverageSimilarity() {
        return averageSimilarity;
    }

    public void setAverageSimilarity(double averageSimilarity) {
        this.averageSimilarity = averageSimilarity;
    }
}