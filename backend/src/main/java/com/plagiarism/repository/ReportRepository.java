package com.plagiarism.repository;

import com.plagiarism.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReportRepository
        extends MongoRepository<Report, String> {

    Optional<Report> findByAssignmentId(String assignmentId);
}