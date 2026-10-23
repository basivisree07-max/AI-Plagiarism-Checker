package com.plagiarism.service;

import com.plagiarism.model.DashboardStats;
import com.plagiarism.model.SimilarityResult;
import com.plagiarism.repository.AssignmentRepository;
import com.plagiarism.repository.SimilarityResultRepository;
import com.plagiarism.repository.SubmissionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final SimilarityResultRepository similarityResultRepository;

    public DashboardService(
            AssignmentRepository assignmentRepository,
            SubmissionRepository submissionRepository,
            SimilarityResultRepository similarityResultRepository) {

        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.similarityResultRepository = similarityResultRepository;
    }

    public DashboardStats getDashboardStats() {

        DashboardStats stats = new DashboardStats();

        // Total assignments
        stats.setTotalAssignments(
                assignmentRepository.count()
        );

        // Total submissions
        stats.setTotalSubmissions(
                submissionRepository.count()
        );

        // Get similarity results
        List<SimilarityResult> results =
                similarityResultRepository.findAll();

        stats.setTotalSimilarityChecks(
                results.size()
        );

        long high = 0;
        long medium = 0;
        long low = 0;

        double totalSimilarity = 0.0;

        for (SimilarityResult result : results) {

            if ("HIGH".equalsIgnoreCase(
                    result.getRiskLevel())) {

                high++;

            } else if ("MEDIUM".equalsIgnoreCase(
                    result.getRiskLevel())) {

                medium++;

            } else if ("LOW".equalsIgnoreCase(
                    result.getRiskLevel())) {

                low++;
            }

            totalSimilarity +=
                    result.getFinalSimilarity();
        }

        stats.setHighRiskPairs(high);
        stats.setMediumRiskPairs(medium);
        stats.setLowRiskPairs(low);

        double average = 0.0;

        if (!results.isEmpty()) {
            average =
                    totalSimilarity / results.size();
        }

        stats.setAverageSimilarity(average);

        return stats;
    }
}