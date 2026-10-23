package com.plagiarism.service;

import com.plagiarism.model.SimilarityResult;
import com.plagiarism.model.Submission;
import com.plagiarism.repository.SimilarityResultRepository;
import com.plagiarism.repository.SubmissionRepository;
import com.plagiarism.similarity.SimilarityEngine;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimilarityService {

    private final SubmissionRepository submissionRepository;
    private final SimilarityResultRepository similarityResultRepository;
    private final SimilarityEngine similarityEngine;

    public SimilarityService(
            SubmissionRepository submissionRepository,
            SimilarityResultRepository similarityResultRepository) {

        this.submissionRepository =
                submissionRepository;

        this.similarityResultRepository =
                similarityResultRepository;

        this.similarityEngine =
                new SimilarityEngine();
    }

    public SimilarityResult compareSubmissions(
            String submission1Id,
            String submission2Id) {

        Submission submission1 =
                submissionRepository
                        .findById(submission1Id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Submission 1 not found"
                                ));

        Submission submission2 =
                submissionRepository
                        .findById(submission2Id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Submission 2 not found"
                                ));

        double[] scores =
                similarityEngine.calculateSimilarity(
                        submission1.getSourceCode(),
                        submission2.getSourceCode()
                );

        double tokenSimilarity = scores[0];
        double jaccardSimilarity = scores[1];
        double cosineSimilarity = scores[2];
        double structuralSimilarity = scores[3];

        double finalSimilarity =
                similarityEngine.calculateFinalSimilarity(
                        tokenSimilarity,
                        jaccardSimilarity,
                        cosineSimilarity,
                        structuralSimilarity
                );

        String riskLevel =
                similarityEngine.determineRiskLevel(
                        finalSimilarity
                );

        SimilarityResult result =
                new SimilarityResult();

        result.setAssignmentId(
                submission1.getAssignmentId()
        );

        result.setSubmission1Id(
                submission1.getId()
        );

        result.setSubmission2Id(
                submission2.getId()
        );

        result.setTokenSimilarity(
                tokenSimilarity
        );

        result.setJaccardSimilarity(
                jaccardSimilarity
        );

        result.setCosineSimilarity(
                cosineSimilarity
        );

        result.setStructuralSimilarity(
                structuralSimilarity
        );

        result.setFinalSimilarity(
                finalSimilarity
        );

        result.setRiskLevel(
                riskLevel
        );

        result.setCreatedAt(
                LocalDateTime.now()
        );

        return similarityResultRepository.save(result);
    }

    public List<SimilarityResult> getAssignmentResults(
            String assignmentId) {

        return similarityResultRepository
                .findByAssignmentId(assignmentId);
    }
}