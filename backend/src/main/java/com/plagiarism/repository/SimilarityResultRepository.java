package com.plagiarism.repository;

import com.plagiarism.model.SimilarityResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SimilarityResultRepository
        extends MongoRepository<SimilarityResult, String> {

    List<SimilarityResult> findByAssignmentId(
            String assignmentId
    );

    List<SimilarityResult> findBySubmission1Id(
            String submission1Id
    );

    List<SimilarityResult> findBySubmission2Id(
            String submission2Id
    );
}