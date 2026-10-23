package com.plagiarism.repository;

import com.plagiarism.model.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubmissionRepository
        extends MongoRepository<Submission, String> {

    List<Submission> findByAssignmentId(String assignmentId);

    List<Submission> findByStudentId(String studentId);
}