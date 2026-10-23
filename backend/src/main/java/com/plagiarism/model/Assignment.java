package com.plagiarism.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "assignments")
public class Assignment {

    @Id
    private String id;

    private String title;
    private String description;
    private String language;
    private String deadline;
    private String teacherId;
    private double similarityThreshold;
    private LocalDateTime createdAt;

    public Assignment() {
    }

    public Assignment(String title,
                      String description,
                      String language,
                      String deadline,
                      String teacherId,
                      double similarityThreshold,
                      LocalDateTime createdAt) {

        this.title = title;
        this.description = description;
        this.language = language;
        this.deadline = deadline;
        this.teacherId = teacherId;
        this.similarityThreshold = similarityThreshold;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public double getSimilarityThreshold() {
        return similarityThreshold;
    }

    public void setSimilarityThreshold(double similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}